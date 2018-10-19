package com.duyj.excel.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.duyj.excel.AbstractWriteExcel;
import org.apache.poi.ss.usermodel.Workbook;
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
        Assert.notEmpty(list, "标题和key不可为空");
        List<ExcelExportEntity> columnList = new ArrayList<>(2);
        for (KeyTitle keyTitle : keyTitleList) {
            ExcelExportEntity column = new ExcelExportEntity();
            column.setName(keyTitle.getTitle());
            column.setKey(keyTitle.getKey());
            columnList.add(column);
        }
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, columnList, list);
        try (OutputStream os = super.getTestOutStream()) {
            workbook.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
