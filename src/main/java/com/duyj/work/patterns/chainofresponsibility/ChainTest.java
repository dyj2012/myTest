package com.duyj.work.patterns.chainofresponsibility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChainTest {

    int k = 0;

    private List<Handler> handlers = null;

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Handler1());
        list.add(new Handler2());

        ChainTest chainTest = new ChainTest();
        chainTest.initHandlers(list);
        chainTest.doChain();
    }

    public void initHandlers(Collection col) {
        handlers = new ArrayList<>(col);
    }

    public void doChain() {
        if (handlers == null) {
            return;
        }
        while (k < handlers.size()) {
            Handler handle = handlers.get(k);
            k++;
            handle.handle();
        }
    }

}
