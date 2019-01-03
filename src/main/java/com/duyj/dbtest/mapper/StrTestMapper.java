package com.duyj.dbtest.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StrTestMapper {

    List<Map<String, Object>> selectTestList(@Param("testStr") String testStr, @Param("map") Map<String, Object> map);

}