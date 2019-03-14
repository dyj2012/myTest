package com.duyj.dbtest;

import com.duyj.dbtest.MysqlTempTable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 跟踪调试日志
 *
 * @author 杜永军
 * @version V1.0
 */
@Aspect
@Component
public class I18nQueryAspect {

    @Autowired
    PlatformTransactionManager transaction;

    @Autowired
    MysqlTempTable mysqlTempTable;

    /**
     * 监控所有DAO层的get和select方法
     * 定义切面点
     */
    @Pointcut("@annotation(com.duyj.dbtest.I18nQuery)")
    public void i18nQuery() {
    }

    /**
     * @param joinPoint 切面点-JoinPoint
     */
    @Around("i18nQuery()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            mysqlTempTable.inited();
            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus status = transaction.getTransaction(defaultTransactionDefinition);
            Object proceed = joinPoint.proceed();
            this.transaction.rollback(status);
            return proceed;
        }finally {
            mysqlTempTable.over();
        }


    }

    private void doCorrect() {
    }
}
