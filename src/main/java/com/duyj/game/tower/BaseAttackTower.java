package com.duyj.game.tower;

import com.duyj.game.AbstractAttackTower;
import com.duyj.game.model.Hurt;
import com.duyj.game.model.TowProperties;

/**
 * 基础塔
 *
 * @author 杜永军
 * @date 2018/10/13
 */
public class BaseAttackTower extends AbstractAttackTower {

    private int baseValue;

    public BaseAttackTower() {
        super(new Reinforce());
    }

    @Override
    public void init(TowProperties properties) {
        baseValue = properties.getBase();
    }

    @Override
    protected void setHurt(Hurt hurt) {
        hurt.setHurtValue(baseValue);
    }
}
