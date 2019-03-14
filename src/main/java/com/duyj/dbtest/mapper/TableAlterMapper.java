package com.duyj.dbtest.mapper;

import com.duyj.dbtest.model.TablePartitionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableAlterMapper {
    /**
     * 增加分区
     *
     * @param param
     */
    void addPartition(TablePartitionVO param);

    /**
     * 查询分区信息
     *
     * @param tableName 表名
     */
    List<TablePartitionVO> selectPartition(@Param("tableName") String tableName);
}