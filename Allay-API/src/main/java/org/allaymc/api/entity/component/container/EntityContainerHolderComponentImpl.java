package org.allaymc.api.entity.component.container;

import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.container.BaseContainerHolder;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.identifier.Identifier;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class EntityContainerHolderComponentImpl extends BaseContainerHolder implements EntityContainerHolderComponent {

    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_holder_component");

    public EntityContainerHolderComponentImpl() {}

    public EntityContainerHolderComponentImpl(Container... containers) {
        super(containers);
    }

    @Override
    public @UnmodifiableView Map<FullContainerType<?>, Container> getContainers() {
        return super.getContainers();
    }

    @Override
    public <T extends Container> T getContainer(FullContainerType<T> type) {
        return super.getContainer(type);
    }

    @Override
    public void addContainer(Container container) {
        super.addContainer(container);
    }
}