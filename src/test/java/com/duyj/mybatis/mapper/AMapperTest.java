package com.duyj.mybatis.mapper;

import com.duyj.mybatis.MybatisBaseTest;
import com.duyj.mybatis.mapper2.BMapper;
import org.junit.Test;
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

    @Test
    public void query() {
        System.out.println(aMapper.query());
        System.out.println(bMapper.query());
    }
}