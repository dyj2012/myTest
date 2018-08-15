package com.duyj.excel.easyexcel;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;

import java.util.List;

/**
 * @author jipengfei
 * @date 2017/08/27
 */
public class TestModelAnalysisListener extends AnalysisEventListener<List<String>> {

    int count = 0;

    @Override
    public void invoke(List<String> object, AnalysisContext context) {
        count++;
        System.out.println(count);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

}
