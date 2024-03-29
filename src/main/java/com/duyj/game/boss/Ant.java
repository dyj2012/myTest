package com.duyj.game.boss;

import com.duyj.game.AbstractBossSpirit;
import com.duyj.game.model.Life;

/**
 * 蚂蚁
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public class Ant extends AbstractBossSpirit {
    @Override
    public String getName() {
        return "ant";
    }

    @Override
    public Life getLifeInfo() {
        return new Life(BASE_BLOOD, 10);
    }
}
