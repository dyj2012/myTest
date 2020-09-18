package com.duyj.mybatis.mapper;

import com.duyj.mybatis.MybatisBaseTest;
import com.duyj.mybatis.mapper2.BMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2020/7/3
 */
public class AMapperTest extends MybatisBaseTest {

    @Autowired
    AMapper aMapper;
    @Autowired
    BMapper bMapper;
    Logger log = LoggerFactory.getLogger(AMapperTest.class);

    @Test
    public void query() {
        log.info("22");
        log.error("1",new Exception());
        System.out.println(aMapper.query());
        System.out.println(bMapper.query());
    }
}