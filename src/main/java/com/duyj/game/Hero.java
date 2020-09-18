package com.duyj.game;

import com.duyj.game.model.Hurt;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public interface Hero extends Spirit{

    /**
     * 获取一次攻击伤害
     *
     * @return
     */
    Hurt attack();

    /**
     * 获取攻击间隔
     *
     * @return
     */
    long getAttackInterval();
}
