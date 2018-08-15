package com.duyj.dbtest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/07/26
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) //加载配置文件
public abstract class BaseTest {
}
