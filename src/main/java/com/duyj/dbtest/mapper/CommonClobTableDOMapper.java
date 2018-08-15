package com.duyj.dbtest.mapper;

import com.duyj.dbtest.entity.CommonClobTableDO;

public interface CommonClobTableDOMapper {
    int insert(CommonClobTableDO record);

    int insertSelective(CommonClobTableDO record);
}