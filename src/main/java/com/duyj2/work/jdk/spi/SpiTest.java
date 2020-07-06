package com.duyj2.work.jdk.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {

    public static void main(String[] args) {

        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);
        Iterator<IOperation> operationIterator = operations.iterator();
        while (operationIterator.hasNext()) {
            IOperation operation = operationIterator.next();
            System.out.println(operation.getClass() + " : " + operation.operation(6, 3));
        }


        for (IOperation oper : operations) {
            int a = oper.operation(100, 20);
            System.out.println(oper.getClass() + " : " + a);
        }

    }
}
