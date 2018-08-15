package com.duyj.dbtest.entity;

public class CommonClobTableDO {
    private String clobId;

    private String clobValue;

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
}