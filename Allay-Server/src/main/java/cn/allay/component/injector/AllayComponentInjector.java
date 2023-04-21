package cn.allay.component.injector;

import cn.allay.component.annotation.Dependency;
import cn.allay.component.annotation.Impl;
import cn.allay.component.annotation.Inject;
import cn.allay.component.exception.ComponentInjectException;
import cn.allay.component.interfaces.*;
import cn.allay.identifier.Identifier;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 * <p>
 * The default injector which use byte-buddy
 */
public class AllayComponentInjector<T> implements ComponentInjector<T> {

    protected static final boolean DEBUG = true;

    protected static final String COMPONENT_LIST_FIELD_NAME = "components";
    protected static final String INIT_METHOD_NAME = "initComponents";
    protected Class<T> parentClass;
    protected List<ComponentProvider<? extends ComponentImpl>> componentProviders = new ArrayList<>();

    public AllayComponentInjector() {
    }

    @Override
    public ComponentInjector<T> parentClass(Class<T> parentClass) {
        Objects.requireNonNull(parentClass, "The parent class cannot be null");
        this.parentClass = parentClass;
        return this;
    }

    @Override
    public ComponentInjector<T> component(List<ComponentProvider<? extends ComponentImpl>> providers) {
        Objects.requireNonNull(providers, "The component providers cannot be null");
        this.componentProviders.addAll(providers);
        return this;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Override
    public Class<T> inject() {
        var bb = new ByteBuddy().subclass(parentClass);
        var componentFieldNameMapping = new HashMap<ComponentProvider<?>, String>();
        int num = 0;
        for (var provider : componentProviders) {
            var fieldName = "f" + num++;
            componentFieldNameMapping.put(provider, fieldName);
            bb = bb.defineField(fieldName, provider.getComponentClass(), Visibility.PRIVATE);
        }
        bb = bb.defineField(COMPONENT_LIST_FIELD_NAME, List.class, Modifier.STATIC | Modifier.PRIVATE);
        bb = buildInitializer(bb, componentFieldNameMapping);
        bb = buildConstructor(bb);
        for (var methodShouldBeInject : Arrays.stream(parentClass.getMethods()).filter(m -> m.isAnnotationPresent(Inject.class)).toList()) {
            var canDuplicate = methodShouldBeInject.getReturnType().equals(Void.class);
            Implementation.Composable methodDelegation = null;
            for (var provider : componentProviders) {
                var componentFieldName = componentFieldNameMapping.get(provider);
                try {
                    Method methodImpl = provider.getComponentClass().getMethod(methodShouldBeInject.getName(), methodShouldBeInject.getParameterTypes());
                    if (!methodImpl.isAnnotationPresent(Impl.class)) continue;
                    if (methodDelegation == null) methodDelegation = MethodDelegation.toField(componentFieldName);
                    else if (canDuplicate)
                        methodDelegation = methodDelegation.andThen(MethodDelegation.toField(componentFieldName));
                    else
                        throw new ComponentInjectException("Duplicate implementation for non-void-return method: " + methodShouldBeInject.getName() + " in " + provider.getComponentClass().getName());
                } catch (NoSuchMethodException ignored) {
                }
            }
            if (methodDelegation == null)
                throw new ComponentInjectException("Missing implementation for method: " + methodShouldBeInject.getName());
            bb = bb.method(is(methodShouldBeInject))
                    .intercept(methodDelegation);
        }
        bb = afterInject(componentProviders, bb);
        try (var unloaded = bb.make()) {
            if (DEBUG) {
                //TODO: improve this
                var file = new File("C:\\Users\\daoge_cmd\\IdeaProjects\\Allay\\Allay-Server\\build\\outclass");
                unloaded.saveIn(file);
            }
            return (Class<T>) unloaded
                    .load(getClass().getClassLoader())
                    .getLoaded();
        } catch (IOException e) {
            throw new ComponentInjectException(e);
        }
    }

    protected DynamicType.Builder<T> buildInitializer(DynamicType.Builder<T> bb, HashMap<ComponentProvider<?>, String> componentFieldNameMapping) {
        bb = bb.defineMethod(INIT_METHOD_NAME, void.class, Modifier.PUBLIC)
                .withParameters(Object.class, ComponentInitInfo.class)
                .intercept(MethodDelegation.to(new Initializer(componentProviders, componentFieldNameMapping)));
        return bb;
    }

    protected DynamicType.Builder<T> buildConstructor(DynamicType.Builder<T> bb) {
        try {
            //Default constructor
            bb = bb.constructor(isDefaultConstructor())
                    .intercept(MethodCall.invoke(Object.class.getDeclaredConstructor())
                            .andThen(MethodCall.invoke(named(INIT_METHOD_NAME)).withThis().with(ComponentInitInfo.EMPTY))
                    );
            //Constructor with ComponentInitInfo
            bb = bb.defineConstructor(Visibility.PUBLIC)
                    .withParameters(ComponentInitInfo.class)
                    .intercept(MethodCall.invoke(Object.class.getDeclaredConstructor())
                            .andThen(MethodCall.invoke(named(INIT_METHOD_NAME)).withThis().withArgument(0))
                    );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return bb;
    }


    protected DynamicType.Builder<T> afterInject(List<ComponentProvider<? extends ComponentImpl>> providers, DynamicType.Builder<T> bb) {
        bb = bb.implement(ComponentedObject.class)
                .method(named("getComponents"))
                .intercept(FieldAccessor.ofField(COMPONENT_LIST_FIELD_NAME));
        return bb;
    }

    public static class Initializer {

        private final List<ComponentProvider<? extends ComponentImpl>> componentProviders;
        private final Map<ComponentProvider<? extends ComponentImpl>, String> componentFieldNameMapping;

        public Initializer(List<ComponentProvider<? extends ComponentImpl>> componentProviders,
                           Map<ComponentProvider<? extends ComponentImpl>, String> componentFieldNameMapping) {
            this.componentProviders = componentProviders;
            this.componentFieldNameMapping = componentFieldNameMapping;
        }

        public void init(Object instance, ComponentInitInfo initInfo) {
            //TODO: 有一些操作可以在构建类的时候完成，这边有待优化
            List<? extends ComponentImpl> components = componentProviders.stream().map(provider -> provider.provide(initInfo)).toList();
            checkComponentValid(components);
            try {
                var componentListField = instance.getClass().getDeclaredField(COMPONENT_LIST_FIELD_NAME);
                componentListField.setAccessible(true);
                componentListField.set(instance, components);
                for (int index = 0; index < components.size(); index++) {
                    var provider = componentProviders.get(index);
                    var component = components.get(index);
                    injectDependency(components, component);
                    var field = instance.getClass().getDeclaredField(componentFieldNameMapping.get(provider));
                    field.setAccessible(true);
                    field.set(instance, component);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        protected void checkComponentValid(List<? extends ComponentImpl> components) {
            Set<Identifier> identifiers = new HashSet<>();
            for (var component : components) {
                var identifier = component.getNamespaceId();
                if (identifiers.contains(identifier))
                    throw new ComponentInjectException("Duplicate component " + component.getNamespaceId());
                else
                    identifiers.add(identifier);
            }
        }

        protected void injectDependency(List<? extends ComponentImpl> components, ComponentImpl component) {
            for (var field : component.getClass().getDeclaredFields()) {
                var annotation = field.getAnnotation(Dependency.class);
                if (annotation != null) {
                    var type = field.getType();
                    List<ComponentImpl> dependencies = new ArrayList<>(components);
                    var count = Integer.MAX_VALUE;
                    var requireCompId = annotation.namespaceId();
                    //Try to find dependencies through inheritance
                    //Try to match by namespace ID
                    if (!requireCompId.isBlank())
                        dependencies = dependencies.stream().filter(dependency -> dependency.getNamespaceId().toString().equals(requireCompId)).toList();
                    else
                        dependencies = dependencies.stream().filter(type::isInstance).toList();
                    count = dependencies.size();
                    //Matches to multiple dependencies
                    if (count > 1)
                        throw new ComponentInjectException("Found multiple dependencies " + type.getName() + " for " + component.getClass().getName());
                    //No dependencies available
                    if (count == 0)
                        throw new ComponentInjectException("Cannot find dependency " + type.getName() + " for " + component.getClass().getName());
                    //Inject dependencies
                    var dependency = dependencies.get(0);
                    field.setAccessible(true);
                    try {
                        field.set(component, dependency);
                    } catch (IllegalAccessException e) {
                        throw new ComponentInjectException("Cannot inject dependency " + type.getName() + " for " + component.getClass().getName(), e);
                    }
                }
            }
        }
    }
}