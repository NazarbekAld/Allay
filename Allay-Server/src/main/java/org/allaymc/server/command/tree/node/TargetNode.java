package org.allaymc.server.command.tree.node;

import com.google.common.collect.Lists;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Collections;
import java.util.List;

/**
 * Allay Project 2024/1/6
 *
 * @author daoge_cmd
 */
public class TargetNode extends BaseNode {

    public TargetNode(String name, CommandNode parent, List<? extends Entity> defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public boolean match(CommandContext context) {
        List<Entity> entities;
        var token = context.queryArg();
        if (EntitySelectorAPI.getAPI().checkValid(token)) {
            try {
                entities = EntitySelectorAPI.getAPI().matchEntities(context.getSender(), token);
            } catch (SelectorSyntaxException exception) {
                return false;
            }
        } else {
            var player = Server.getInstance().findOnlinePlayerByName(token);
            if (player != null) {
                entities = Lists.newArrayList(player);
            } else {
                // 未找到目标不认为是非法的，这在一些命令里面有特殊用处 (eg: /testfor)
                entities = Collections.emptyList();
            }
        }
        filterResult(entities);
        context.putResult(entities);
        context.popArg();
        return true;
    }

    protected void filterResult(List<Entity> entities) {
        // Do nothing
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.TARGET);
        return data;
    }
}
