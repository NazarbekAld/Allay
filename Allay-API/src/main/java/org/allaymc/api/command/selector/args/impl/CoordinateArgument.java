package org.allaymc.api.command.selector.args.impl;

import org.allaymc.api.command.selector.args.ISelectorArgument;

/**
 * args like x,y,z.
 */


public abstract class CoordinateArgument implements ISelectorArgument {

    @Override
    public int getPriority() {
        return 1;
    }
}
