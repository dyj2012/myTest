package com.duyj.game;

import com.duyj.game.model.Hurt;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public abstract class AbstractPlayer {

    /**
     * 基础间隔
     */
    public final int BASE_INTERVAL = 1000;

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
