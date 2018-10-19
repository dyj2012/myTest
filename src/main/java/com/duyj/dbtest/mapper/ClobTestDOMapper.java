package com.duyj.dbtest.mapper;

import com.duyj.dbtest.entity.ClobTestDO;

import java.util.List;
import java.util.Map;

public interface ClobTestDOMapper {

    int insert(ClobTestDO record);

    int batchInsert(List<ClobTestDO> list);

    int insertSelective(ClobTestDO record);

    List<ClobTestDO> selectClobTestDOList1();

    List<ClobTestDO> selectClobTestDOList2();

    List<ClobTestDO> selectClobTestDOList3();

    List<ClobTestDO> selectClobTestDOList4();

    List<Map<String, Object>> selectClobTestDOList5();
}