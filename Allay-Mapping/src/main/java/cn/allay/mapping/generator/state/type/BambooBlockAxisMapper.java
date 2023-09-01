package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "axis", blockRegex = "minecraft:(stripped_)?bamboo_block")
public class BambooBlockAxisMapper extends StateMapper<String> {

    @Override
    public Pair<String, String> translateState(String fullIdentifier, String value) {
        return Pair.of("pillar_axis", value);
    }
}