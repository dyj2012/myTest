package com.duyj.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 写Excel
 *
 * @author 杜永军
 * @date 2018/08/25
 */
public abstract class AbstractWriteExcel {

    /**
     * 导出Excel
     *
     * @param list
     */
    public abstract void writeExcel(List<Map<String, Object>> list, List<KeyTitle> keyTitleList);

    protected OutputStream getTestOutStream() {
        try {
            return new FileOutputStream("F:/test.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static class KeyTitle {
        private final String key;
        private final String title;

        public KeyTitle(String key, String title) {
            this.key = key;
            this.title = title;
        }

        public String getKey() {
            return key;
        }

        public String getTitle() {
            return title;
        }
    }

}
