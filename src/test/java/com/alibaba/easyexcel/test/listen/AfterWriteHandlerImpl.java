package com.alibaba.easyexcel.test.listen;

import com.alibaba.excel.event.WriteHandler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class AfterWriteHandlerImpl implements WriteHandler {

    //
    CellStyle cellStyle;

    @Override
    public void sheet(int sheetNo, Sheet sheet) {


    }

    @Override
    public void row(int rowNum, Row row) {

    }

    @Override
    public void cell(int cellNum, Cell cell) {

    }
}
