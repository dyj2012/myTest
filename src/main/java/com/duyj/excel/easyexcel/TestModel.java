package com.duyj.excel.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/08
 */
public class TestModel extends BaseRowModel {
    @ExcelProperty(index = 0)
    private String str0;
    @ExcelProperty(index = 1)
    private String str1;
    @ExcelProperty(index = 2)
    private String str2;
    @ExcelProperty(index = 3)
    private String str3;
    @ExcelProperty(index = 4)
    private String str4;
    @ExcelProperty(index = 5)
    private String str5;
    @ExcelProperty(index = 6)
    private String str6;
    @ExcelProperty(index = 7)
    private String str7;
    @ExcelProperty(index = 8)
    private String str8;
    @ExcelProperty(index = 9)
    private String str9;

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public String getStr4() {
        return str4;
    }

    public void setStr4(String str4) {
        this.str4 = str4;
    }

    public String getStr5() {
        return str5;
    }

    public void setStr5(String str5) {
        this.str5 = str5;
    }

    public String getStr6() {
        return str6;
    }

    public void setStr6(String str6) {
        this.str6 = str6;
    }

    public String getStr7() {
        return str7;
    }

    public void setStr7(String str7) {
        this.str7 = str7;
    }

    public String getStr8() {
        return str8;
    }

    public void setStr8(String str8) {
        this.str8 = str8;
    }

    public String getStr9() {
        return str9;
    }

    public void setStr9(String str9) {
        this.str9 = str9;
    }

    public String getStr0() {
        return str0;
    }

    public void setStr0(String str0) {
        this.str0 = str0;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "str0='" + str0 + '\'' +
                ", str1='" + str1 + '\'' +
                ", str2='" + str2 + '\'' +
                ", str3='" + str3 + '\'' +
                ", str4='" + str4 + '\'' +
                ", str5='" + str5 + '\'' +
                ", str6='" + str6 + '\'' +
                ", str7='" + str7 + '\'' +
                ", str8='" + str8 + '\'' +
                ", str9='" + str9 + '\'' +
                '}';
    }
}
