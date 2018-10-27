package com.duyj.game.tower;

import com.duyj.game.AbstractAttackTower;
import com.duyj.game.model.Hurt;
import com.duyj.game.model.TowProperties;

import java.math.BigDecimal;

/**
 * 暴击
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public class Crit extends AbstractAttackTower {

    private BigDecimal critValue;

    public Crit() {
        super(null);
    }

    @Override
    public void init(TowProperties properties) {
        critValue = properties.getCrit();
    }

    @Override
    protected void setHurt(Hurt hurt) {
        hurt.setHurtValue(critValue.multiply(BigDecimal.valueOf(hurt.getHurtValue())).intValue());
    }
}
