package com.duyj.game;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public abstract class AbstractPlayer {

    /**
     * 获取一次攻击伤害
     *
     * @return
     */
    public abstract Hurt getHurt();

    /**
     * 获取攻击间隔
     *
     * @return
     */
    public abstract long getAttackInterval();
}
