package com.duyj.dbbatch;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Mybatis批量操作服务帮助类
 *
 * @author 杜永军
 * @date 2019/02/27
 */
@Component
public class BatchOperationHandler {

    @Resource(name = "batchSqlSession")
    private SqlSessionTemplate sqlSessionTemplate;

    public <T> Object batchUpdate(Class<T> t, BatchCallback<T> consumer) {
        T mapper = (T) sqlSessionTemplate.getMapper(t);
        return consumer.doBatch(mapper);
    }

}
