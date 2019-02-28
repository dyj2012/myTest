package com.duyj.dbbatch;

/**
 * 回调
 *
 * @author 杜永军
 * @date 2019/02/28
 */
public interface BatchCallback<T> {

	Object doBatch(T t);
}
