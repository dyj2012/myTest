package com.duyj.dbtest.mapper;

import com.duyj.dbtest.BaseTest;
import com.duyj.dbtest.entity.TestPartDO;
import com.duyj.dbtest.model.TablePartitionVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2019/03/14
 */
public class TestPartDOMapperTest extends BaseTest {

    @Autowired
    private TestPartMapper testPartDOMapper;
    @Autowired
    private TableAlterMapper tableAlterMapper;

    @Test
    public void insert() {
        TestPartDO testPartDO = new TestPartDO();
        testPartDO.setId(UUID.randomUUID().toString());
        testPartDO.setDate(LocalDate.now().plusYears(10));
        testPartDOMapper.insert(testPartDO);
    }


    @Test
    public void test1() {
        List<TablePartitionVO> test_part = tableAlterMapper.selectPartition("test_part");
        System.out.println(test_part);

    }


}