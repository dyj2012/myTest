package com.duyj.excel;

import java.io.InputStream;

/**
 * 读取excel
 *
 * @author 杜永军
 * @date 2018/08/08
 */
public abstract class AbstractReadExcel {

    public abstract void readExcel(String fileUrl);

    protected InputStream getFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

    }
}
