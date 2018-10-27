package com.duyj.game.tower;

import com.duyj.game.AbstractAttackTower;
import com.duyj.game.AbstractTower;
import com.duyj.game.model.Hurt;
import com.duyj.game.model.TowProperties;

/**
 * 暴击率
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public class CritProbability extends AbstractAttackTower {
    public CritProbability() {
        super(new Crit());
    }

    private int probability;

    @Override
    public void init(TowProperties properties) {
        probability = properties.getCritProbability();
    }

    @Override
    protected void setHurt(Hurt hurt) {

    }

    @Override
    public void computeHurt(Hurt hurt) {
        if (probability > random.nextInt(AbstractTower.RANDOM_BOUND)) {
            super.computeHurt(hurt);
        }
    }

}
