package com.duyj.game;

import java.math.BigDecimal;

/**
 * 价值
 *
 * @author 杜永军
 * @date 2020/7/8
 */
public interface Value {

    /**
     * 币种
     *
     * @return
     */
    Currency getCurrency();

    /**
     * 数量
     *
     * @return
     */
    BigDecimal getNum();
}
