package com.duyj.dbtest.entity;

public class ClobTestDO {
    private String column1;

    private String column2;

    private String column3;

    private String column4;

    private String clobId;

    private String clobValue;

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getClobId() {
        return clobId;
    }

    public void setClobId(String clobId) {
        this.clobId = clobId;
    }

    public String getClobValue() {
        return clobValue;
    }

    public void setClobValue(String clobValue) {
        this.clobValue = clobValue == null ? null : clobValue.trim();
    }

    @Override
    public String toString() {
        return "\nClobTestDO{" +
                "column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                ", column4='" + column4 + '\'' +
                ", clobId='" + clobId + '\'' +
                ", clobValue='" + clobValue + '\'' +
                '}';
    }
}