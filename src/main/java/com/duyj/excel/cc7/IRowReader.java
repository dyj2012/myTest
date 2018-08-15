package com.duyj.excel.cc7;

import java.util.List;

public interface IRowReader {
    /**
     * 业务逻辑实现方法
     *
     * @param sheetIndex
     * @param curRow
     * @param cellList
     * @param rowCount
     */
    void getRows(int sheetIndex, int curRow, List<String> cellList, int rowCount);
}
