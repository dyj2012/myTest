package com.duyj.excel.easyexcel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.duyj.excel.AbstractWriteExcel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/07/30
 */
public class WriteExcel {

    public void test1() {
        try (OutputStream out = new FileOutputStream("F:/78.xls")) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0, TestModel.class);
            writer.write(getData(), sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<? extends BaseRowModel> getData() {
        List l = new ArrayList(1);
        TestModel excelPropertyIndexModel = new TestModel();
        excelPropertyIndexModel.setStr0("test");
        l.add(excelPropertyIndexModel);
        l.add(excelPropertyIndexModel);
        l.add(excelPropertyIndexModel);
        l.add(excelPropertyIndexModel);
        l.add(excelPropertyIndexModel);
        l.add(excelPropertyIndexModel);
        return l;
    }
}
