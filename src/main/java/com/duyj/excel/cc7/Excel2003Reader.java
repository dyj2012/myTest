package com.duyj.excel.cc7;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.hssf.eventusermodel.*;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽象Excel2003读取器，通过实现HSSFListener监听器，采用事件驱动模式解析excel2003
 * 中的内容，遇到特定事件才会触发，大大减少了内存的使用。
 */
public class Excel2003Reader implements HSSFListener {
    private int minColumns = -1;
    private int lastRowNumber;
    private int lastColumnNumber;
    private static final String[] WORKBOOK_DIR_ENTRY_NAMES = new String[]{"Workbook", "WORKBOOK", "BOOK"};
    /**
     * Should we output the formula, or the value it has?
     */
    private boolean outputFormulaValues = true;
    /**
     * For parsing Formulas
     */
    private EventWorkbookBuilder.SheetRecordCollectingListener workbookBuildingListener;
    //excel2003工作薄
    private HSSFWorkbook stubWorkbook;

    // Records we pick up as we process
    private SSTRecord sstRecord;
    private FormatTrackingHSSFListener formatListener;

    //表索引
    private int sheetIndex = -1;
    private BoundSheetRecord[] orderedBSRs;
    @SuppressWarnings("unchecked")
    private ArrayList<BoundSheetRecord> boundSheetRecords = new ArrayList<BoundSheetRecord>();

    // For handling formulas with string results
    private int nextRow;
    private int nextColumn;
    private boolean outputNextStringRecord;
    //当前行
    private int curRow = 0;
    //存储行记录的容器
    private List<String> rowlist = new ArrayList<>();

    @SuppressWarnings("unused")
    private String sheetName;
    private int countRow = 0;
    HSSFWorkbook workbook;

    private IRowReader rowReader;


    public void setRowReader(IRowReader rowReader) {
        this.rowReader = rowReader;
    }


    public static String getWorkbookDirEntryName(DirectoryNode directory) {
        int i = 0;
        while (i < WORKBOOK_DIR_ENTRY_NAMES.length) {
            String wbName = WORKBOOK_DIR_ENTRY_NAMES[i];
            try {
                directory.getEntry(wbName);
                return wbName;
            } catch (FileNotFoundException var6) {
                ++i;
            }
        }
        try {
            directory.getEntry("EncryptedPackage");
            throw new EncryptedDocumentException("The supplied spreadsheet seems to be an Encrypted .xlsx file. It must be decrypted before use by XSSF, it cannot be used by HSSF");
        } catch (FileNotFoundException var5) {
            try {
                directory.getEntry("Book");
                throw new OldExcelFormatException("The supplied spreadsheet seems to be Excel 5.0/7.0 (BIFF5) format. POI only supports BIFF8 format (from Excel versions 97/2000/XP/2003)");
            } catch (FileNotFoundException var4) {
                throw new IllegalArgumentException("The supplied POIFSFileSystem does not contain a BIFF8 'Workbook' entry. Is it really an excel file?");
            }
        }
    }

    /**
     * 遍历excel下所有的sheet
     *
     * @throws IOException
     */
    public void process(InputStream inputStream) throws IOException {
        workbook = new HSSFWorkbook(inputStream);
        MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
        formatListener = new FormatTrackingHSSFListener(listener);
        HSSFEventFactory factory = new HSSFEventFactory();
        HSSFRequest request = new HSSFRequest();
        if (outputFormulaValues) {
            request.addListenerForAllRecords(formatListener);
        } else {
            workbookBuildingListener = new EventWorkbookBuilder.SheetRecordCollectingListener(formatListener);
            request.addListenerForAllRecords(workbookBuildingListener);
        }
        factory.processWorkbookEvents(request, workbook.getRootDirectory());

    }

    /**
     * HSSFListener 监听方法，处理 Record
     */
    @Override
    public void processRecord(Record record) {
        int thisRow = -1;
        int thisColumn = -1;
        String thisStr = null;
        String value = null;
        switch (record.getSid()) {
            case BoundSheetRecord.sid:
                boundSheetRecords.add((BoundSheetRecord) record);
                break;
            case BOFRecord.sid:
                BOFRecord br = (BOFRecord) record;
                if (br.getType() == BOFRecord.TYPE_WORKSHEET) {
                    sheetIndex++;
                    if (orderedBSRs == null) {
                        orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
                    }
                    sheetName = orderedBSRs[sheetIndex].getSheetname();
                }
                break;
            case SSTRecord.sid:
                sstRecord = (SSTRecord) record;
                break;

            case BlankRecord.sid:
                BlankRecord brec = (BlankRecord) record;
                thisRow = brec.getRow();
                thisColumn = brec.getColumn();
                thisStr = "";
                rowlist.add(thisColumn, thisStr);
                break;
            //单元格为布尔类型
            case BoolErrRecord.sid:
                BoolErrRecord berec = (BoolErrRecord) record;
                thisRow = berec.getRow();
                thisColumn = berec.getColumn();
                thisStr = berec.getBooleanValue() + "";
                rowlist.add(thisColumn, thisStr);
                break;
            //单元格为公式类型
            case FormulaRecord.sid:
                FormulaRecord frec = (FormulaRecord) record;
                thisRow = frec.getRow();
                thisColumn = frec.getColumn();
                if (outputFormulaValues) {
                    if (Double.isNaN(frec.getValue())) {
                        // Formula result is a string
                        // This is stored in the next record
                        outputNextStringRecord = true;
                        nextRow = frec.getRow();
                        nextColumn = frec.getColumn();
                    } else {
                        thisStr = formatListener.formatNumberDateCell(frec);
                    }
                } else {
                    thisStr = '"' + HSSFFormulaParser.toFormulaString(stubWorkbook,
                            frec.getParsedExpression()) + '"';
                }
                rowlist.add(thisColumn, thisStr);
                break;
            //单元格中公式的字符串
            case StringRecord.sid:
                if (outputNextStringRecord) {
                    // String for formula
                    StringRecord srec = (StringRecord) record;
                    thisStr = srec.getString();
                    thisRow = nextRow;
                    thisColumn = nextColumn;
                    outputNextStringRecord = false;
                }
                break;
            case LabelRecord.sid:
                LabelRecord lrec = (LabelRecord) record;
                curRow = thisRow = lrec.getRow();
                thisColumn = lrec.getColumn();
                value = lrec.getValue().trim();
                value = value.equals("") ? " " : value;
                this.rowlist.add(thisColumn, value);
                break;
            //单元格为字符串类型
            case LabelSSTRecord.sid:
                LabelSSTRecord lsrec = (LabelSSTRecord) record;
                curRow = thisRow = lsrec.getRow();
                thisColumn = lsrec.getColumn();
                if (sstRecord == null) {
                    rowlist.add(thisColumn, " ");
                } else {
                    value = sstRecord
                            .getString(lsrec.getSSTIndex()).toString().trim();
                    value = value.equals("") ? " " : value;
                    rowlist.add(thisColumn, value);
                }
                break;
            case NumberRecord.sid:  //单元格为数字类型
                NumberRecord numrec = (NumberRecord) record;
                curRow = thisRow = numrec.getRow();
                thisColumn = numrec.getColumn();
                value = formatListener.formatNumberDateCell(numrec).trim();
                value = value.equals("") ? " " : value;
                // 向容器加入列值
                rowlist.add(thisColumn, value);
                break;
            default:
                break;
        }

        // 遇到新行的操作
        if (thisRow != -1 && thisRow != lastRowNumber) {
            lastColumnNumber = -1;
        }

        // 空值的操作
        if (record instanceof MissingCellDummyRecord) {
            MissingCellDummyRecord mc = (MissingCellDummyRecord) record;
            curRow = thisRow = mc.getRow();
            thisColumn = mc.getColumn();
            rowlist.add(thisColumn, " ");
        }

        // 更新行和列的值
        if (thisRow > -1) {
            lastRowNumber = thisRow;
        }
        if (thisColumn > -1) {
            lastColumnNumber = thisColumn;
        }

        // 行结束时的操作
        if (record instanceof LastCellOfRowDummyRecord) {
            // 列值重新置空
            if (minColumns > 0 && lastColumnNumber == -1) {
                lastColumnNumber = 0;
            }
            lastColumnNumber = -1;
            // 每行结束时， 调用getRows() 方法
            rowReader.getRows(sheetIndex, curRow, rowlist, countRow);
            // 清空容器
            rowlist.clear();
        }
    }

}
