package com.duyj.table;

/**
 * 表管理服务
 *
 * @author 杜永军
 * @date 2019/03/14
 */
public interface TableManager {

    /**
     * 增加分区
     *
     * @param tableName
     * @param partValue
     */
    void addPartition(String tableName, String partValue);
}
