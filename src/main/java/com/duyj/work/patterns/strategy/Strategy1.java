package com.duyj.work.patterns.strategy;

import com.duyj.work.utils.Q;

/**
 * 策略1
 */
class Strategy1 implements IStrategy{

    @Override
    public void printf(String[] msg){
        StringBuilder sb = new StringBuilder();
        for (String str : msg) {
            sb.append("[");
            sb.append(str);
            sb.append("]");
        }
        Q.p(sb.toString());
    }

}
