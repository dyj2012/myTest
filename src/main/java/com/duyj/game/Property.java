package com.duyj.game;

import java.math.BigDecimal;

/**
 * 属性
 *
 * @author 杜永军
 * @date 2018/09/15
 */
public interface Property {

    /**
     * 类型
     *
     * @return
     */
    String getType();

    /**
     * 类型
     *
     * @return
     */
    String getName();

    /**
     * 优先级
     *
     * @return
     */
    double getPriority();

    /**
     * 值
     *
     * @return
     */
    BigDecimal getValue();


}
