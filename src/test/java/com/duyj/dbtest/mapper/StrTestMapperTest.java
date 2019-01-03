package com.duyj.dbtest.mapper;

import com.duyj.dbtest.BaseTest;
import com.duyj.dbtest.MysqlTempTable;
import com.duyj.dbtest.entity.ClobTestDO;
import com.duyj.uitl.BatchSaveUtils;
import com.duyj.uitl.IDGenerator;
import com.duyj.uitl.PrintTimeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/14
 */
public class StrTestMapperTest extends BaseTest {

    @Autowired
    StrTestMapper clobTestDOMapper;

    @Test
    public void select() {
        Map<String, Object> map = new HashMap();
        map.put("num", 5);
        List<Map<String, Object>> list1 = clobTestDOMapper.selectTestList("and rowNum < #{map.num}", map);
        System.out.println(list1.size());
        map.put("num", 3);
        List<Map<String, Object>> list2 = clobTestDOMapper.selectTestList("and rowNum < #{map.num}", map);
        System.out.println(list2.size());
    }

}