package com.duyj2.work.jdk.myenum;

public enum MyEnum {
	
	ONE(1){public void print(){System.out.println(this.toString());}},
	
	TWO(2){public void print(){System.out.println(this.ordinal());}},
	
	THREE(3),
	
	FORE(4);
	
	private int value;
	
	public String desc;
	
	MyEnum(int a){
		System.out.println(super.name());
		value = a;
	}
	
	public void print(){
		System.out.println(this.value);
	}
	
	public static void main(String[] args) {
		
		MyEnum.FORE.value=123;
		
		MyEnum.FORE.print();
		
		MyEnum a  =MyEnum.valueOf("FORE");
		
	}

}
