package com.duyj.excel.cc7;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * 抽象Excel2007读取器，excel2007的底层数据结构是xml文件，采用SAX的事件驱动的方法解析
 * xml，需要继承DefaultHandler，在遇到文件内容时，事件会触发，这种做法可以大大降低
 * 内存的耗费，特别使用于大数据量的文件。
 */
public class Excel2007Reader extends DefaultHandler {

    enum XssfDataType {
        BOOL, ERROR, FORMULA, INLINE_STR, SST_INDEX, NUMBER,
    }

    /**
     * 共享字符串表
     */
    private SharedStringsTable sst;
    /**
     * 内容
     */
    private StringBuilder lastContents = new StringBuilder();
    private int sheetIndex = -1;
    private List<String[]> rowList = new ArrayList<>();
    /**
     * 当前行
     */
    private int curRow = 0;
    /**
     * 当前列
     */
    private int curCol = -1;
    private boolean vIsOpen;
    private XssfDataType nextDataType;
    private StylesTable stylesTable;
    private short formatIndex;
    private String formatString;
    private DataFormatter formatter = new DataFormatter();
    private int lastColumnNumber = -1;
    private boolean isCellNull = false;
    private String[] record = new String[99];
    private int rowCount = 0;
    /**
     * 存放标题以外的值
     */
    private List<String> otherValueList = new ArrayList<>();
    private IRowReader rowReader;

    public void setRowReader(IRowReader rowReader) {
        this.rowReader = rowReader;
    }

    /**
     * 遍历工作簿中所有的电子表格
     *
     * @param inputStream
     * @throws Exception
     */
    public void process(InputStream inputStream) throws Exception {
        OPCPackage pkg = OPCPackage.open(inputStream);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);
        Iterator<InputStream> sheets = r.getSheetsData();
        this.stylesTable = r.getStylesTable();
        while (sheets.hasNext()) {
            curRow = 0;
            sheetIndex++;
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
        }
    }


    public XMLReader fetchSheetParser(SharedStringsTable sst)
            throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        this.sst = sst;
        parser.setContentHandler(this);
        return parser;
    }

    /**
     * Converts an Excel column name like "C" to a zero-based index.
     *
     * @param name
     */
    private int nameToColumn(String name) {
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        if ("inlineStr".equals(name) || "v".equals(name) || "t".equals(name)) {
            vIsOpen = true;
            lastContents.setLength(0);
        }
        // c => 单元格
        if ("c".equals(name)) {
            // Get the cell reference
            String r = attributes.getValue("r");
            int firstDigit = -1;
            for (int c = 0; c < r.length(); ++c) {
                if (Character.isDigit(r.charAt(c))) {
                    firstDigit = c;
                    break;
                }
            }
            curCol = nameToColumn(r.substring(0, firstDigit));
            this.nextDataType = XssfDataType.NUMBER;
            this.formatIndex = -1;
            this.formatString = null;
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue("t");
            String cellStyleStr = attributes.getValue("s");
            if ("b".equals(cellType)) {
                nextDataType = XssfDataType.BOOL;
            } else if ("e".equals(cellType)) {
                nextDataType = XssfDataType.ERROR;
            } else if ("inlineStr".equals(cellType)) {
                nextDataType = XssfDataType.INLINE_STR;
            } else if ("s".equals(cellType)) {
                nextDataType = XssfDataType.SST_INDEX;
            } else if ("str".equals(cellType)) {
                nextDataType = XssfDataType.FORMULA;
            } else if (cellStyleStr != null) {
                int styleIndex = Integer.parseInt(cellStyleStr);
                XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                this.formatIndex = style.getDataFormat();
                this.formatString = style.getDataFormatString();
                if (this.formatString == null) {
                    this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
                }
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        // 根据SST的索引值的到单元格的真正要存储的字符串
        // 这时characters()方法可能会被调用多次
        String thisStr = null;
        if ("v".equals(name) || "t".equals(name)) {
            if (nextDataType == XssfDataType.BOOL) {
                char first = lastContents.charAt(0);
                thisStr = first == '0' ? "FALSE" : "TRUE";
            } else if (nextDataType == XssfDataType.ERROR) {
                thisStr = "\"ERROR:" + lastContents.toString() + '"';
            } else if (nextDataType == XssfDataType.FORMULA) {
                thisStr = lastContents.toString();
            } else if (nextDataType == XssfDataType.INLINE_STR) {
                XSSFRichTextString rtsi = new XSSFRichTextString(lastContents.toString());
                thisStr = rtsi.toString();
            } else if (nextDataType == XssfDataType.SST_INDEX) {
                String sstIndex = lastContents.toString();
                try {
                    int idx = Integer.parseInt(sstIndex);
                    XSSFRichTextString rtss = new XSSFRichTextString(sst.getEntryAt(idx));
                    thisStr = rtss.toString();
                } catch (NumberFormatException ex) {
                    throw new SAXException("Failed to parse SST index '" + sstIndex + "': " + ex.toString());
                }
            } else if (nextDataType == XssfDataType.NUMBER) {
                String n = lastContents.toString();
                // 判断是否是日期格式
                if (HSSFDateUtil.isADateFormat(this.formatIndex, n)) {
                    Double d = Double.parseDouble(n);
                    Date date = HSSFDateUtil.getJavaDate(d);
                    thisStr = formatDateToString(date);
                } else if (this.formatString != null) {
                    thisStr = formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex, this.formatString);
                } else {
                    thisStr = n;
                }
            } else {
                thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
            }

            if (lastColumnNumber == -1) {
                lastColumnNumber = 0;
            }
            //判断单元格的值是否为空
            if (thisStr == null || "".equals(isCellNull)) {
                // 设置单元格是否为空值
                isCellNull = true;
            }
            if (record[0] == null) {
                record[0] = "";
            }
            if (curCol > 0) {
                if (record[curCol - 1] == null) {
                    record[curCol - 1] = "";
                }
            }
            if (curCol + 1 <= record.length) {
                record[curCol] = thisStr;
            } else {
                otherValueList.add(thisStr);
            }
            // Update column
            if (curCol > -1) {
                lastColumnNumber = curCol;
            }
            //如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
        } else if ("row".equals(name)) {
            if (lastColumnNumber == -1) {
                lastColumnNumber = 0;
            }
            if (isCellNull == false && record[0] != null && record[1] != null) {
                //当list为空的时候，更变count值
                if (rowList.size() == 0) {
                    List<String> ls = new ArrayList<String>();
                    for (int i = 0; i < record.length; i++) {
                        String isnull = record[i];
                        if (isnull == null || "".equals(isnull)) {
                            break;
                        } else {
                            ls.add(isnull);
                        }
                    }
                    rowList.add(ls.toArray(new String[ls.size()]));
                    isCellNull = false;
                    for (int i = 0; i < record.length; i++) {
                        record[i] = null;
                    }
                    record = null;
                    record = new String[ls.size()];
                    ls.clear();
                } else {
                    for (int i = 0; i < record.length; i++) {
                        if (record[i] == null) {
                            record[i] = "";
                        }
                    }
                    rowList.add(record.clone());
                    isCellNull = false;
                    for (int i = 0; i < record.length; i++) {
                        record[i] = null;
                    }
                }
            }
            List<String> strings = new ArrayList<>();
            strings.clear();
            if (rowList.size() > 0) {
                //标题
                if (curRow == 0) {
                    String[] oneRow = rowList.get(0);
                    for (int i = 0; i < oneRow.length; i++) {
                        strings.add(oneRow[i]);
                    }
                } else {
                    //处理不是空行的数据
                    if (rowList.size() >= 2) {
                        String[] valueRow = rowList.get(1);
                        rowList.clear();
                        rowList.add(0, new String[]{});
                        for (int i = 0; i < valueRow.length; i++) {
                            strings.add(valueRow[i] == null ? "" : valueRow[i]);
                        }
                    } else {
                        throw new RuntimeException("第[" + (curRow + 1) + "]行数据为空!不能导入数据!");
                    }
                }
            }
            if (otherValueList.size() > 0) {
                strings.addAll(otherValueList);
            }
            rowReader.getRows(sheetIndex, curRow, strings, rowCount);
            strings.clear();
            otherValueList.clear();
            curRow++;
            lastColumnNumber = -1;
        }

    }

    private String formatDateToString(Date date) {
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (vIsOpen) {
            //得到单元格内容的值
            lastContents.append(ch, start, length);
        }
    }
}