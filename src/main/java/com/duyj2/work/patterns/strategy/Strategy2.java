package com.duyj2.work.patterns.strategy;

import com.duyj2.work.utils.Q;

/**
 * Created by LG on 2017/3/12.
 */
class Strategy2 implements IStrategy{

	@Override
	public void printf(String[] msg) {
		StringBuffer sb= new StringBuffer();
	    for(String str: msg){
	        sb.append(str);
	        sb.append(",");
        }
        Q.p(sb.toString());
    }

}
