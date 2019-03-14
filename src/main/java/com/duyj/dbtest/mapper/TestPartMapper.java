package com.duyj.dbtest.mapper;

import com.duyj.dbtest.entity.TestPartDO;

public interface TestPartMapper {
    int deleteByPrimaryKey(TestPartDO key);

    int insert(TestPartDO record);
}