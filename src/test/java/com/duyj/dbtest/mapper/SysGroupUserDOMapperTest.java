package com.duyj.dbtest.mapper;

import com.duyj.dbtest.BaseTest;
import com.duyj.dbtest.entity.SysGroupUserDO;
import com.duyj.uitl.BatchSaveUtils;
import com.duyj.uitl.UUIDUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/07/26
 */

public class SysGroupUserDOMapperTest extends BaseTest {

    @Autowired
    SysGroupUserDOMapper sysGroupUserDOMapper;

    private static int count = 1000;

    @Test
    public void saveSysGroupUser() {
        List<SysGroupUserDO> sysGroupUserDOList = this.getSysGroupUserDOList(count);
        long s1 = System.currentTimeMillis();
        for (SysGroupUserDO sysGroupUserDO : sysGroupUserDOList) {
            sysGroupUserDOMapper.insert(sysGroupUserDO);
        }
        long time = System.currentTimeMillis() - s1;
        System.out.println(String.format("foreach save count %s time %sms", count, time));
    }

    @Test
    public void saveSysGroupUserBatchPer1K() {
        List<SysGroupUserDO> sysGroupUserDOList = this.getSysGroupUserDOList(count);
        BatchSaveUtils.batchSave(sysGroupUserDOList, 1000, m -> sysGroupUserDOMapper.insertBatch(m));
    }

    @Test
    public void saveSysGroupUserBatch() {
        sysGroupUserDOMapper.insert(this.getSysGroupUserDO());
        List<SysGroupUserDO> sysGroupUserDOList = this.getSysGroupUserDOList(count);
        long s1 = System.currentTimeMillis();
        sysGroupUserDOMapper.insertBatch(sysGroupUserDOList);
        long time = System.currentTimeMillis() - s1;
        System.out.println(String.format("batch save count %s time %sms", count, time));
    }

    /**
     * @param count
     * @return
     */
    private List<SysGroupUserDO> getSysGroupUserDOList(int count) {
        List<SysGroupUserDO> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(this.getSysGroupUserDO());
        }
        return list;
    }

    private SysGroupUserDO getSysGroupUserDO() {
        SysGroupUserDO sysGroupUserDO = new SysGroupUserDO();
        sysGroupUserDO.setGroupId(UUIDUtils.getUUID());
        sysGroupUserDO.setCreateDate(LocalDateTime.now());
        sysGroupUserDO.setGroupUserId(UUIDUtils.getUUID());
        sysGroupUserDO.setUpdateDate(LocalDateTime.now());
        sysGroupUserDO.setUserId(UUIDUtils.getUUID());
        sysGroupUserDO.setGroupId(UUIDUtils.getUUID());
        sysGroupUserDO.setStatus("1");
        return sysGroupUserDO;
    }


}