package com.duyj.game.boss;

import com.duyj.game.AbstractBossSpirit;
import com.duyj.game.Life;

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
    public long getTimeLimit() {
        return 15 * 1000;
    }

    @Override
    public Life getLifeInfo() {
        return new Life(1000, 10);
    }
}
