package com.duyj.dbtest;

import com.duyj.dbtest.mapper.ClobTestDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/12/27
 */
@Component
public class MysqlTempTable {

    @Autowired
    ClobTestDOMapper clobTestDOMapper;
    private ThreadLocal<Object> inited = new ThreadLocal<>();

    public void inited() {
        inited.set(new Object());
    }

    public void over() {
        inited.remove();
    }

    @I18nQuery
    public void query1() {
        this.insert();
        this.query();
    }

    @I18nQuery
    public void query2() {
        this.insert();
        this.query();
    }

    @I18nQuery
    public void query3() {
        this.insert();
        this.insert();
        this.query();
    }

    private void insert() {
        if (inited.get() == null) {
            throw new RuntimeException("查询方法未增加I18nQuery注解!不允许检索!");
        }
        clobTestDOMapper.createTempTable();
        clobTestDOMapper.insertTemp();
    }


    public void query() {
        List<Map<String, Object>> maps = clobTestDOMapper.selectTemp();
        System.out.println(maps);
    }



}
