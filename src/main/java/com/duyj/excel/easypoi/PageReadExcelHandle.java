package com.duyj.excel.easypoi;

import cn.afterturn.easypoi.handler.inter.IExcelReadRowHandler;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/09
 */
public class PageReadExcelHandle<T> implements IExcelReadRowHandler<T> {
    int count = 0;

    @Override
    public void hanlder(T o) {
        System.out.println(o);
        count++;
        System.out.println(count);
    }
}
