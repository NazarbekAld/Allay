package org.allaymc.api.command.selector.args.impl;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.ParseUtils;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.SelectorType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3f;

import java.util.function.Predicate;

public class Z extends CoordinateArgument {
    @Override
    public Predicate<Entity> getPredicate(SelectorType selectorType, CommandSender sender, Location3f basePos, String... arguments) throws SelectorSyntaxException {
        ParseUtils.singleArgument(arguments, getKeyName());
        ParseUtils.cannotReversed(arguments[0]);
        basePos.z = ParseUtils.parseOffsetFloat(arguments[0], basePos.z);
        return null;
    }

    @Override
    public String getKeyName() {
        return "z";
    }
}