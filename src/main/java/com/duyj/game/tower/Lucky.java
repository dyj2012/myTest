package com.duyj.game.tower;

import com.duyj.game.AbstractTower;
import com.duyj.game.model.TowProperties;

/**
 * 攻速,减少攻击间隔
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public class Lucky extends AbstractTower {

    private int luckyValue;

    @Override
    public void init(TowProperties properties) {
        luckyValue = properties.getLucky();
    }

}
