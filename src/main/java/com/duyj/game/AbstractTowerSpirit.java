package com.duyj.game;

/**
 * 精灵抽象类
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public abstract class AbstractTowerSpirit {

    private AbstractTowerSpirit next;

    public AbstractTowerSpirit(AbstractTowerSpirit next) {
        this.next = next;
    }

    protected abstract void computeHurt(Hurt hurt);

    public void computeHurtTemplate(Hurt hurt) {
        this.computeHurt(hurt);
        if (next != null) {
            next.computeHurtTemplate(hurt);
        }
    }
}
