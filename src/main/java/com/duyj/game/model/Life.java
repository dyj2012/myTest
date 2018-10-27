package com.duyj.game.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 基础生命
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public class Life {
    /**
     * 血条
     */
    private long blood;
    /**
     * 物理护盾
     */
    private long physicsDefense;

    public Life(long blood, long physicsDefense) {
        this.blood = blood;
        this.physicsDefense = physicsDefense;
    }

    public long getBlood() {
        return blood;
    }

    public void setBlood(long blood) {
        this.blood = blood;
    }

    public long getPhysicsDefense() {
        return physicsDefense;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("blood", blood);
        builder.append("physicsDefense", physicsDefense);
        return builder.toString();
    }
}
