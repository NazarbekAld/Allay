package cn.allay.math.position;

import cn.allay.level.Level;
import lombok.EqualsAndHashCode;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
@EqualsAndHashCode
class ImplPosRO<T extends Number> implements PosRO<T> {

    protected T x;
    protected T y;
    protected T z;
    protected Level level;

    public ImplPosRO(T x, T y, T z, Level level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.level = level;
    }

    @Override
    public T getX() {
        return x;
    }

    @Override
    public T getY() {
        return y;
    }

    @Override
    public T getZ() {
        return z;
    }

    @Override
    public Level getLevel() {
        return level;
    }
}
