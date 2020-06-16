/**
 * 元年软件
 *
 * @author 王亚平
 * @date 2018年12月29日 上午11:07:28
 * @version V1.0
 */
package com.duyj.aop;

import com.duyj.table.TableManager;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TablePartitionAspect {
    private static final String SUB_SQL = "### SQL:";
    private static final String EXCEPTION_SQL = "Table has no partition for value";

    @Autowired
    private TableManager tableManager;

    /**
     * 监控所有DAO层的get和select方法
     * 定义切面点
     */
    @Pointcut("execution(* * ..*Mapper.insert*(..))||execution(* * ..*Mapper.save*(..))")
    public void insertMonitor() {
    }


    /**
     * @param joinPoint 切面点-JoinPoint
     */
    @Around("insertMonitor()")
    public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return doProceed(joinPoint);
        } catch (Exception e) {
            if (e.getMessage().contains("Table has no partition for value")) {
                String s = e.toString();
                System.out.println(s);
                String tableName = StringUtils.substringBetween(s, SUB_SQL, "(").trim();
                String partValue = StringUtils.substringBetween(s, EXCEPTION_SQL, "\r\n");
                System.out.println(tableName.substring(tableName.lastIndexOf(" ")).trim());
                tableManager.addPartition(tableName.substring(tableName.lastIndexOf(" ")).trim(), partValue.trim());
                return doProceed(joinPoint);
            } else {
                throw e;
            }
        }
    }

    public Object doProceed(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }


}
