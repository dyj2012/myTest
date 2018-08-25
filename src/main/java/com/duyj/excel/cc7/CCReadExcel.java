package com.duyj.excel.cc7;

import com.duyj.excel.AbstractReadExcel;

import java.util.List;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/08
 */
public class CCReadExcel extends AbstractReadExcel {
    @Override
    public void readExcel(String fileUrl) {
        IRowReader iRowReader = (int sheetIndex, int curRow, List<String> cellList, int rowCount) -> {
            System.out.println(curRow);
        };
        try {
            ExcelReaderUtil.readExcel(iRowReader, fileUrl, this.getFileInputStream(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
