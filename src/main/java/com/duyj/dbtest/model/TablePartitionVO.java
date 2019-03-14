package com.duyj.dbtest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 添加表
 *
 * @author 杜永军
 * @date 2019/03/14
 */
public class TablePartitionVO {

    private String tableName;
    private String partName;
    private Integer year;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("tableName", tableName);
        builder.append("partName", partName);
        builder.append("year", year);
        return builder.toString() + "\n";
    }
}
