//package com.duyj.excel.easyexcel;
//
//import com.alibaba.excel.ExcelReader;
//import com.alibaba.excel.support.ExcelTypeEnum;
//import com.duyj.excel.AbstractReadExcel;
//
//import java.io.InputStream;
//
///**
// * <description>
// *
// * @author 杜永军
// * @date 2018/07/30
// */
//public class EasyExcelReadExcel extends AbstractReadExcel {
//
//    private void read2003Excel(String url) {
//        try (InputStream inputStream = this.getFileInputStream(url)) {
//            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null,
//                    new TestModelAnalysisListener());
//            reader.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void read2007Excel(String url) {
//        try (InputStream inputStream = this.getFileInputStream(url)) {
//            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null,
//                    new TestModelAnalysisListener());
//            reader.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public void readExcel(String fileUrl) {
//        if (fileUrl.endsWith(ExcelTypeEnum.XLS.getValue())) {
//            this.read2003Excel(fileUrl);
//        } else {
//            this.read2007Excel(fileUrl);
//        }
//    }
//}
