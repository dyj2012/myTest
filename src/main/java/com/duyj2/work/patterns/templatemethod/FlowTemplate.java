package com.duyj2.work.patterns.templatemethod;

/**
 * Created by LG on 2017/3/16.
 */
public class FlowTemplate extends AbstractTemplate{

    @Override
    public void process_2(){
        System.out.println("步骤2圆满完成！");
    }

    public void process_3(){
        System.out.println("步骤3圆满完成！");
    }
}
