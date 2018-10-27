package com.duyj.game;

import com.duyj.game.model.Hurt;

/**
 * 精灵抽象类
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public abstract class AbstractAttackTower extends AbstractTower {

    private AbstractAttackTower next;

    public AbstractAttackTower(AbstractAttackTower next) {
        this.next = next;
    }

    /**
     * 计算一次伤害
     *
     * @param hurt
     */
    protected abstract void setHurt(Hurt hurt);

    /**
     * 计算伤害模板
     *
     * @param hurt
     */
    public void computeHurt(Hurt hurt) {
        this.setHurt(hurt);
        if (next != null) {
            next.computeHurt(hurt);
        }
    }
}
