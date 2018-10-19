package com.duyj.game;

import com.duyj.game.boss.Ant;
import com.duyj.game.player.GeneralPlayer;
import org.junit.Test;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/13
 */
public class AttackSceneTest {

    @Test
    public void attack() {
        AttackScene attackScene = new AttackScene();
        attackScene.attack(new Ant(), new GeneralPlayer(), new ShowCallback());
    }
}