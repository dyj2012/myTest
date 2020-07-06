package com.duyj.mybatis.mapper2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BMapper {

//    @Select("select 'bbb'")
    String query();
}