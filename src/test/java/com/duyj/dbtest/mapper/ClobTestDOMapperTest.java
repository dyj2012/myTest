package com.duyj.dbtest.mapper;

import com.duyj.dbtest.BaseTest;
import com.duyj.dbtest.entity.ClobTestDO;
import com.duyj.uitl.BatchSaveUtils;
import com.duyj.uitl.IDGenerator;
import com.duyj.uitl.PrintTimeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/14
 */
public class ClobTestDOMapperTest extends BaseTest {

    @Autowired
    ClobTestDOMapper clobTestDOMapper;

    @Test
    public void insert() {
        List<ClobTestDO> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(getClobTestDO());
        }
        PrintTimeUtils.time(() -> BatchSaveUtils.batchSave(list, 500, subList -> clobTestDOMapper.batchInsert(subList)));
        clobTestDOMapper.insert(getClobTestDO());
    }

    private ClobTestDO getClobTestDO() {
        ClobTestDO clobTestDO = new ClobTestDO();
        String s = IDGenerator.newID();
        clobTestDO.setColumn1(s);
        clobTestDO.setColumn2(s);
        clobTestDO.setColumn3(s);
        clobTestDO.setColumn4(s);
        clobTestDO.setClobId(s);
        clobTestDO.setClobValue(String.format("%s%s%s", s, s, s));
        return clobTestDO;
    }

    @Test
    public void select() {
        clobTestDOMapper.selectClobTestDOList1();
        PrintTimeUtils.time(() -> clobTestDOMapper.selectClobTestDOList1(), "带Clob字段表,但不查询clob字段:");
        PrintTimeUtils.time(() -> clobTestDOMapper.selectClobTestDOList2(), "不带Clob字段表:");
        PrintTimeUtils.time(() -> clobTestDOMapper.selectClobTestDOList3(), "带Clob字段表,但查询clob字段:");
        PrintTimeUtils.time(() -> clobTestDOMapper.selectClobTestDOList4(), "不带Clob字段表,连接查询clob字段:");
    }

}