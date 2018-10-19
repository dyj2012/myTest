package com.duyj.game;

import com.duyj.game.scene.BaseScene;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 攻击场景
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public class AttackScene {

    public void attack(final AbstractBossSpirit boss, AbstractPlayer player, ShowCallback callback) {
        final BaseScene scene = new BaseScene(callback, boss.getLifeInfo());
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "Attack-" + boss.getName()));
        //0表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
        scheduled.schedule(scene::setTimeout, boss.getTimeLimit(), TimeUnit.MILLISECONDS);
        scheduled.scheduleAtFixedRate(() -> scene.fireAttack(player.getHurt()), 0, player.getAttackInterval(), TimeUnit.MILLISECONDS);
        while (!scene.isAttackOver()) {
            Thread.yield();
        }
        scheduled.shutdownNow();
    }



}
