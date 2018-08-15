package com.duyj.dbtest.mapper;

import com.duyj.dbtest.entity.SysGroupUserDO;

import java.util.List;

public interface SysGroupUserDOMapper {
    int deleteByPrimaryKey(String groupUserId);

    int insert(SysGroupUserDO record);

    void insertBatch(List<SysGroupUserDO> list);

    int insertSelective(SysGroupUserDO record);

    SysGroupUserDO selectByPrimaryKey(String groupUserId);

    int updateByPrimaryKeySelective(SysGroupUserDO record);

    int updateByPrimaryKey(SysGroupUserDO record);
}