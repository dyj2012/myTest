package com.duyj.dbtest.mapper;

import com.duyj.dbtest.entity.ClobTestDO;

import java.util.List;

public interface ClobTestDOMapper {

    int insert(ClobTestDO record);

    int batchInsert(List<ClobTestDO> list);

    int insertSelective(ClobTestDO record);

    List<ClobTestDO> selectClobTestDOList(List<String> list);
}