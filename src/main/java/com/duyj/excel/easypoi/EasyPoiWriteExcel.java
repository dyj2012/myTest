package com.duyj.excel.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import cn.afterturn.easypoi.excel.export.styler.ExcelExportStylerColorImpl;
import com.duyj.excel.AbstractWriteExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 杜永军
 * @date 2018/08/08
 */
public class EasyPoiWriteExcel extends AbstractWriteExcel {

    @Override
    public void writeExcel(List<Map<String, Object>> list, List<KeyTitle> keyTitleList) {
        Assert.notEmpty(list, "导出数据不能为空");
        Assert.notEmpty(keyTitleList, "标题和key不可为空");
        List<ExcelExportEntity> columnList = this.getColumnList(keyTitleList);
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        exportParams.setHeight((short) 6);
        exportParams.setTitleHeight((short) 6);
        exportParams.setStyle(MyStyle.class);



        Workbook workbook = getWorkbook(exportParams.getType(),list.size());
        new MyExcelExportService().createSheetForMap(workbook, exportParams, columnList, list);
        //return workbook;
        //Workbook workbook = ExcelExportUtil.exportExcel(exportParams, columnList, list);
        try (OutputStream os = super.getTestOutStream()) {
            workbook.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Workbook getWorkbook(ExcelType type, int size) {
        if (ExcelType.HSSF.equals(type)) {
            return new HSSFWorkbook();
        } else if (size < 100000) {
            return new XSSFWorkbook();
        } else {
            return new SXSSFWorkbook();
        }
    }


    private List<ExcelExportEntity> getColumnList(List<KeyTitle> keyTitleList) {
        List<ExcelExportEntity> columnList = new ArrayList<>(2);
        for (KeyTitle keyTitle : keyTitleList) {
            ExcelExportEntity column = new ExcelExportEntity();
            column.setName(keyTitle.getTitle());
            column.setKey(keyTitle.getKey());
            //column.setMergeVertical(true);
            column.setNeedMerge(true);
            List<KeyTitle> subTitleList = keyTitle.getSubTitleList();
            if (subTitleList != null) {
                column.setList(getColumnList(subTitleList));
            }
            columnList.add(column);
        }
        return columnList;
    }
}
