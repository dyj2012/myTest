package com.duyj.game;

/**
 * boss抽象类
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public abstract class AbstractBossSpirit {

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
    public abstract long getTimeLimit();

    /**
     * 获取生命信息
     *
     * @return
     */
    public abstract Life getLifeInfo();


}
