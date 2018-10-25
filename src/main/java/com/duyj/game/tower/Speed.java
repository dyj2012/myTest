package com.duyj.game.tower;

import com.duyj.game.AbstractTowerSpirit;
import com.duyj.game.Hurt;

/**
 * 攻速
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public class Speed extends AbstractTowerSpirit {
    public Speed(AbstractTowerSpirit next) {
        super(next);
    }

    @Override
    protected void computeHurt(Hurt hurt) {

    }
}
