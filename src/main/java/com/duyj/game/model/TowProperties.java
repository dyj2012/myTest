package com.duyj.game.model;

import java.math.BigDecimal;

/**
 * 塔的数值
 *
 * @author 杜永军
 * @date 2018/10/13
 */
public class TowProperties {

    /**
     * 基础
     */
    private int base = 100;

    /**
     * 强击百分比
     */
    private BigDecimal reinforce = BigDecimal.valueOf(0.2);

    /**
     * 暴击率
     */
    private int critProbability = 20;

    /**
     * 暴击率
     */
    private BigDecimal crit = BigDecimal.valueOf(2);

    /**
     * 减防
     */
    private BigDecimal reducedDefense = BigDecimal.valueOf(2);

    /**
     * 穿透
     */
    private int penetrate = 10;

    /**
     * 运气
     */
    private int lucky = 50;

    /**
     * 攻速
     */
    private BigDecimal speed = BigDecimal.valueOf(0.2);

    /**
     * 时间
     */
    private int time = 0;

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public BigDecimal getReinforce() {
        return reinforce;
    }

    public void setReinforce(BigDecimal reinforce) {
        this.reinforce = reinforce;
    }

    public int getCritProbability() {
        return critProbability;
    }

    public void setCritProbability(int critProbability) {
        this.critProbability = critProbability;
    }

    public BigDecimal getCrit() {
        return crit;
    }

    public void setCrit(BigDecimal crit) {
        this.crit = crit;
    }

    public BigDecimal getReducedDefense() {
        return reducedDefense;
    }

    public void setReducedDefense(BigDecimal reducedDefense) {
        this.reducedDefense = reducedDefense;
    }

    public int getPenetrate() {
        return penetrate;
    }

    public void setPenetrate(int penetrate) {
        this.penetrate = penetrate;
    }

    public int getLucky() {
        return lucky;
    }

    public void setLucky(int lucky) {
        this.lucky = lucky;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
