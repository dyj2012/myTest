package com.duyj2.work.patterns.templatemethod;

/**
 * Created by LG on 2017/3/16.
 */
public class TemplateMethodTest{

    public static void main(String[] args){
        ITemplate flow = new FlowTemplate();
        flow.method();
    }
}
