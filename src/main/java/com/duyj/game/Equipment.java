package com.duyj.game;

import java.util.List;

/**
 * 装备
 *
 * @author 杜永军
 * @date 2020/7/8
 */
public interface Equipment extends Spirit {

    /**
     * 装备价值
     *
     * @return
     */
    List<Value> getValue();
}
