package com.duyj.dbtest.mapper;

import com.duyj.dbtest.BaseTest;
import com.duyj.dbtest.SerialServer;
import com.duyj.uitl.ThreadUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/14
 */
public class NumMapperTest extends BaseTest {

    @Autowired
    SerialServer serialServer;
    private static final int COUNT = 100;

    @Test
    public void test1() {
        ThreadUtils.run(COUNT, a -> {
            for (int i = 0; i < 1; i++) {
                System.out.println(serialServer.f1());
            }
        });
        System.out.println("test1");
    }

    @Test
    public void test2() {
        ThreadUtils.run(COUNT, a -> {
            for (int i = 0; i < 1; i++) {
                System.out.println(serialServer.f2());
            }
        });
        System.out.println("test2");
    }

    @Test
    public void test3() {
        ThreadUtils.run(COUNT, a -> {
            for (int i = 0; i < 1; i++) {
                System.out.println(serialServer.f3());
            }
        });
        System.out.println("test3");
    }


}