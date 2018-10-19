package com.duyj.game.player;

import com.duyj.game.AbstractPlayer;
import com.duyj.game.Hurt;

/**
 * 普通玩家
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public class GeneralPlayer extends AbstractPlayer {
    @Override
    public Hurt getHurt() {
        return new Hurt();
    }

    @Override
    public long getAttackInterval() {
        return 1000;
    }
}
