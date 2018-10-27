package com.duyj.game;

import com.duyj.game.model.Life;

/**
 * boss抽象类
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public abstract class AbstractBossSpirit {

    /**
     * 基础血条
     */
    public static int BASE_BLOOD = 1000;
    /**
     * 秒
     */
    public static int SECOND = 1000;
    /**
     * 基础时间
     */
    public static int BASE_TIME = 10;
    /**
     * 基础护盾
     */
    public static int DEFENSE = 100;

    /**
     * 名称
     *
     * @return
     */
    public abstract String getName();

    /**
     * 攻击时间限制
     *
     * @return
     */
    public long getTimeLimit() {
        return BASE_TIME * SECOND;
    }

    /**
     * 获取生命信息
     *
     * @return
     */
    public abstract Life getLifeInfo();


}
