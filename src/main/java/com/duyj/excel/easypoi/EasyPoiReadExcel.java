package com.duyj.excel.easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.duyj.excel.ReadExcel;

import java.io.InputStream;
import java.util.List;

/**
 *
 *
 * @author 杜永军
 * @date 2018/08/08
 */
public class EasyPoiReadExcel extends ReadExcel {

    @Override
    public void readExcel(String fileUrl) {
        InputStream fileInputStream = getFileInputStream(fileUrl);
        if (fileUrl.endsWith(".xls")) {
            this.readExcel03(fileInputStream);
        } else {
            this.readExcel07(fileInputStream);
        }
    }

    public void readExcel03(InputStream inputStream) {
        try {
            List<Object> objects = ExcelImportUtil.importExcel(inputStream, TestModel.class, new ImportParams());
            System.out.println(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readExcel07(InputStream inputStream) {
        ExcelImportUtil.importExcelBySax(inputStream, TestModel.class, new ImportParams(), new PageReadExcelHandle<>());
    }
}
