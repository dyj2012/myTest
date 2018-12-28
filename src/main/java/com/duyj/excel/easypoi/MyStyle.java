package com.duyj.excel.easypoi;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.styler.ExcelExportStylerColorImpl;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/12/19
 */
public class MyStyle extends ExcelExportStylerColorImpl {
    public MyStyle(Workbook workbook) {
        super(workbook);
    }

    @Override
    public CellStyle getTitleStyle(short color) {
        CellStyle headerStyle = super.getTitleStyle(color);
        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setBorderLeft(BorderStyle.MEDIUM);
        headerStyle.setBorderRight(BorderStyle.MEDIUM);
        headerStyle.setBorderTop(BorderStyle.MEDIUM);
        return headerStyle;
    }

    @Override
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data) {
        System.out.println(entity);
        System.out.println(obj);
        System.out.println(data);
        return getStyles(dataRow % 2 == 1, entity);
    }
}
