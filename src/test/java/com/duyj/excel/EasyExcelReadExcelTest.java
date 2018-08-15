package com.duyj.excel;

import com.duyj.uitl.PrintTimeUtils;
import com.duyj.excel.cc7.CCReadExcel;
import com.duyj.excel.easyexcel.EasyExcelReadExcel;
import com.duyj.excel.easypoi.EasyPoiReadExcel;
import org.junit.Test;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/07/31
 */
public class EasyExcelReadExcelTest {
    EasyExcelReadExcel easyExcelReadExcel = new EasyExcelReadExcel();
    CCReadExcel ccReadExcel = new CCReadExcel();
    EasyPoiReadExcel easyPoiReadExcel = new EasyPoiReadExcel();
    //    String filePath = "test6w.xls";
    String filePath = "test20w.xlsx";

    @Test
    public void easyExcelRead() {
        PrintTimeUtils.time(() -> easyExcelReadExcel.readExcel(filePath));
    }

    @Test
    public void cc7ExcelRead() {
        PrintTimeUtils.time(() -> ccReadExcel.readExcel(filePath));
    }

    @Test
    public void easyPoiRead() {
        PrintTimeUtils.time(() -> easyPoiReadExcel.readExcel(filePath));
    }


}