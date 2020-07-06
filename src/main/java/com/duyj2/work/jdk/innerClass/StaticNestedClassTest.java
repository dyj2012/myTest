package com.duyj2.work.jdk.innerClass;

class OuterClass {
	private static String name="OuterClass";
	//在外部类中使用静态内部类
	private Nest1 nest1 = new Nest1();
	
	public void printNest1(){
		nest1.print();
	}
	private static class Nest1 {
		public void print() {
			// 只能访问外部类的静态成员
			System.out.println(this +" name:"+ name);
		}
	}
	public static class Nest2 {
		Nest2(){
			System.out.println(this);
		}
	}
}

public class StaticNestedClassTest {
	public static void main(String[] args) {
		OuterClass out1 = new OuterClass();
		OuterClass out2 = new OuterClass();
		out1.printNest1();
		out2.printNest1();
		// public静态内部类 无需外部类即可创建
		OuterClass.Nest2 nest2 = new OuterClass.Nest2();
	}

}
