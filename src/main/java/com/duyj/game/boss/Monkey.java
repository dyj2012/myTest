package com.duyj.game.boss;

import com.duyj.game.AbstractBossSpirit;
import com.duyj.game.model.Life;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/27
 */
public class Monkey extends AbstractBossSpirit {
    @Override
    public String getName() {
        return "Monkey";
    }

    @Override
    public Life getLifeInfo() {
        return new Life(2 * BASE_BLOOD, 10);
    }
}
