package com.duyj.excel.easypoi;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/12/19
 */
public class MyExcelExportService extends ExcelExportService {

    /**
     * 创建文本类型的Cell
     */
    @Override
    public void createStringCell(Row row, int index, String text, CellStyle style,
                                 ExcelExportEntity entity) {
        super.createStringCell(row, index, text, style, entity);
        Cell cell1 = row.getCell(index);
        Cell cell = row.createCell(index);
        CellStyle styles = excelExportStyler.getStyles(cell1, index, entity, text, text);

    }
}
