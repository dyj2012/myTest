package com.duyj.game.tower;

import com.duyj.game.AbstractTowerSpirit;
import com.duyj.game.Hurt;

/**
 * 暴击
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public class Crit extends AbstractTowerSpirit {
    public Crit(AbstractTowerSpirit next) {
        super(next);
    }

    @Override
    protected void computeHurt(Hurt hurt) {

    }
}
