package com.duyj.game;

import com.duyj.game.model.TowProperties;

import java.util.Random;

/**
 * 精灵抽象类
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public abstract class AbstractTower {

    public Random random = new Random();
    public final static int RANDOM_BOUND = 100;

    /**
     * 初始化塔的数值
     *
     * @param properties
     */
    public abstract void init(TowProperties properties);

}
