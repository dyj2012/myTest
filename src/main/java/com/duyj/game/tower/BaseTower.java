package com.duyj.game.tower;

import com.duyj.game.AbstractTowerSpirit;
import com.duyj.game.Hurt;

/**
 * 基础塔
 *
 * @author 杜永军
 * @date 2018/10/13
 */
public class BaseTower extends AbstractTowerSpirit {

    public BaseTower(AbstractTowerSpirit next) {
        super(next);
    }

    @Override
    protected void computeHurt(Hurt hurt) {
        hurt.setBaseHurt(100);
    }


}
