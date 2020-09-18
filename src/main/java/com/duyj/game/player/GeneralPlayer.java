package com.duyj.game.player;

import com.duyj.game.Player;
import com.duyj.game.Property;
import com.duyj.game.model.Hurt;

import java.util.List;

/**
 * 普通玩家
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public class GeneralPlayer implements Player {
    @Override
    public Hurt attack() {
        return null;
    }

    @Override
    public Hurt getHurt() {
        return new Hurt();
    }

    @Override
    public long getAttackInterval() {
        return 0;
    }

    @Override
    public List<Property> getProperties() {
        return null;
    }
}
