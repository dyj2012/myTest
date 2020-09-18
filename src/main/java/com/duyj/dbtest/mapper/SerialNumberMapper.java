package com.duyj.dbtest.mapper;

import org.apache.ibatis.annotations.Param;

public interface SerialNumberMapper {
    int increase(@Param("key") Long key);

    long lockRow(@Param("key") Long key);

    long getNum(@Param("key") Long key);

    long insertRow();

    long getSequence();
}