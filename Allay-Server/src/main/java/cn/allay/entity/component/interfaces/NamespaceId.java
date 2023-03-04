package cn.allay.entity.component.interfaces;

import cn.allay.component.annotation.Inject;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface NamespaceId {
    @Inject
    Identifier getNamespaceId();
}
