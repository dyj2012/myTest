package com.duyj.dbtest.entity;

import java.time.LocalDate;

public class TestPartDO {
    private String id;

    private LocalDate date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}