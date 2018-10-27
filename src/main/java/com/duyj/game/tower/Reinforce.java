package com.duyj.game.tower;

import com.duyj.game.AbstractAttackTower;
import com.duyj.game.model.TowProperties;
import com.duyj.game.model.Hurt;

import java.math.BigDecimal;

/**
 * 强击,增加基础力量百分比
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public class Reinforce extends AbstractAttackTower {

    private BigDecimal reinforceValue;

    public Reinforce() {
        super(new Crit());
    }

    @Override
    public void init(TowProperties properties) {

    }

    @Override
    protected void setHurt(Hurt hurt) {

    }

}
