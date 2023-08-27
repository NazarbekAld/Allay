package cn.allay.codegen;

import cn.allay.dependence.BiomeType;
import cn.allay.dependence.Identifier;
import com.squareup.javapoet.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Allay Project 2023/6/3
 *
 * @author daoge_cmd
 */
public class VanillaBiomeIdEnumGen {
    public static void main(String[] args) {
        generate();
    }

    private static final Map<String, BiomeData> BIOME_DATA = new LinkedHashMap<>();

    private static class BiomeData {
        int id;
        String type;
    }

    static {
        try {
            Map<String, BiomeData> unsorted = CodeGen.GSON.fromJson(Files.newBufferedReader(Path.of("Data/unpacked/biome_id_and_type.json")), new HashMap<String, BiomeData>() {
            }.getClass().getGenericSuperclass());
            unsorted.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(o -> o.id)))
                    .forEachOrdered(entry -> BIOME_DATA.put(entry.getKey(), entry.getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ClassName STRING_CLASS = ClassName.get("java.lang", "String");
    private static final Class<?> IDENTIFIER_CLASS = Identifier.class;
    private static final Class<?> INT_CLASS = int.class;
    private static final ClassName GETTER_CLASS = ClassName.get("lombok", "Getter");
    private static final String PACKAGE_NAME = "cn.allay.api.data";
    private static final Path TARGET_PATH = Path.of("Allay-API/src/main/java/cn/allay/api/data/VanillaBiomeId.java");
    private static final String JAVA_DOC = """
            Get from number id or identifier using {@link cn.allay.api.utils.VanillaBiomeIdUtils VanillaBiomeIdUtils}
                   
            Automatically generated by {@code cn.allay.codegen.VanillaBiomeIdEnumGen} <br>
            Allay Project <p>
            @author daoge_cmd | Cool_Loong
            """;

    @SneakyThrows
    public static void generate() {
        TypeSpec.Builder codeBuilder = TypeSpec.enumBuilder("VanillaBiomeId")
                .addJavadoc(JAVA_DOC)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(GETTER_CLASS)
                .addStaticBlock(
                        CodeBlock.builder()
                                .add("MAP1 = $L;", "new Int2ObjectOpenHashMap<>()")
                                .add("MAP2 = $L;", "new HashMap<>()")
                                .add("""
                                                
                                        for (var b : values()) {
                                            MAP1.put(b.id, b);
                                            MAP2.put(b.identifier, b);
                                        }""")
                                .build()
                )
                .addField(FieldSpec
                        .builder(Int2ObjectOpenHashMap.class, "MAP1", Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC)
                        .build())
                .addField(FieldSpec
                        .builder(HashMap.class, "MAP2", Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC)
                        .build())
                .addField(FieldSpec
                        .builder(IDENTIFIER_CLASS, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .build())
                .addField(FieldSpec
                        .builder(INT_CLASS, "id", Modifier.PRIVATE, Modifier.FINAL)
                        .build())
                .addField(FieldSpec
                        .builder(STRING_CLASS, "type", Modifier.PRIVATE, Modifier.FINAL)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(IDENTIFIER_CLASS, "identifier")
                        .addParameter(INT_CLASS, "id")
                        .addParameter(STRING_CLASS, "type")
                        .addStatement("this.identifier = identifier")
                        .addStatement("this.id = id")
                        .addStatement("this.type = type")
                        .build()
                );
        for (var entry : BIOME_DATA.entrySet()) {
            var identifier = """
                    new Identifier("minecraft:%s")""".formatted(entry.getKey());
            var id = entry.getValue().id;
            var type = entry.getValue().type;
            codeBuilder.addEnumConstant(entry.getKey().toUpperCase(), TypeSpec.anonymousClassBuilder("$L, $L, $S", identifier, id, type).build());
        }
        codeBuilder.addSuperinterface(BiomeType.class);

        codeBuilder.addMethod(MethodSpec
                .methodBuilder("fromId")
                .addParameter(INT_CLASS, "id")
                .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                .addCode("return MAP1.get(id);")
                .addAnnotation(Nullable.class)
                .returns(BiomeType.class)
                .build());
        codeBuilder.addMethod(MethodSpec
                .methodBuilder("fromIdentifier")
                .addParameter(IDENTIFIER_CLASS, "identifier")
                .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                .addCode("return MAP2.get(identifier);")
                .addAnnotation(Nullable.class)
                .returns(BiomeType.class)
                .build());
        var builtCode = codeBuilder.build();
        var javaFile = JavaFile.builder(PACKAGE_NAME, builtCode).build();
        String result = javaFile.toString()
                .replace("cn.allay.dependence.BiomeType", "cn.allay.api.world.biome.BiomeType")
                .replace("cn.allay.dependence.Identifier", "cn.allay.api.identifier.Identifier")
                .replace(" Int2ObjectOpenHashMap ", " Int2ObjectOpenHashMap<VanillaBiomeId> ")
                .replace(" HashMap ", " HashMap<Identifier, VanillaBiomeId> ");
        Files.writeString(TARGET_PATH, result);
    }
}
