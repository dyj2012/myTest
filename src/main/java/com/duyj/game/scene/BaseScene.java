package com.duyj.game.scene;

import com.duyj.game.ShowCallback;
import com.duyj.game.model.Hurt;
import com.duyj.game.model.Life;

import java.math.BigDecimal;

/**
 * 基础场景
 *
 * @author 杜永军
 * @date 2018/10/12
 */
public class BaseScene {

    private boolean timeout = false;
    private boolean lifeOver = false;
    private ShowCallback callback;
    private Life life;
    private static final BigDecimal DEFENSE_BASE = BigDecimal.valueOf(1000);

    public BaseScene(ShowCallback callback, Life life) {
        this.callback = callback;
        this.life = life;
    }

    public void setTimeout() {
        callback.printInfo("attack timeout");
        this.timeout = true;
    }

    /**
     * 被攻击
     *
     * @param hurt
     */
    public void fireAttack(Hurt hurt) {
        if (isAttackOver()) {
            return;
        }
        BigDecimal hurtValue = BigDecimal.valueOf(hurt.getHurtValue());
        BigDecimal physicsShield = BigDecimal.valueOf(life.getPhysicsDefense());
        if (physicsShield.compareTo(BigDecimal.ZERO) > 0) {
            hurtValue = hurtValue.subtract(hurtValue.multiply(physicsShield.divide(DEFENSE_BASE)));
        }
        life.setBlood(life.getBlood() - hurtValue.longValue());
        callback.printInfo(life.toString());
        if (life.getBlood() <= 0) {
            lifeOver = true;
            callback.printInfo("life is dead");
        }
    }


    /**
     * 攻击结束
     *
     * @return
     */
    public boolean isAttackOver() {
        return timeout || lifeOver;
    }
}
