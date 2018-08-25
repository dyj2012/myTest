package com.duyj.excel.easypoi;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.sax.SaxReadExcel;
import com.duyj.excel.AbstractReadExcel;

import java.io.InputStream;
import java.util.Map;

/**
 * @author 杜永军
 * @date 2018/08/08
 */
public class EasyPoiReadExcel extends AbstractReadExcel {

    @Override
    public void readExcel(String fileUrl) {
        try {
            this.readExcel07(getFileInputStream(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readExcel07(InputStream inputStream) throws Exception {
        ImportParams params = new ImportParams();
        new SaxReadExcel().readExcel(inputStream, Map.class, params, new MapSaxRowRead(params, new PageReadExcelHandle<>()), null);
    }
}
