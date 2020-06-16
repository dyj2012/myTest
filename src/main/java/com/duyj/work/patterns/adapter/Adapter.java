package com.duyj.work.patterns.adapter;
//适配器
//通过包装将一个类的接口转换成客户希望的另外一个接口。　
public class Adapter implements Target{
	
	//需要被适配的类
	private Adaptee adaptee = new Adaptee();
    
    public Adapter(){}
    
    /**
     * 将需要适配的类中的方法转为客户端需要的方法
     */
	@Override
	public void targetMethod1() {
	    this.adaptee.localMethod1();
	}

    @Override
    public void targetMethod2() {
	    this.adaptee.localMethod2();
    }

}
