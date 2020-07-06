package com.duyj2.work.jdk.innerClass;

interface ForInner1 {
	void printSomething();
}

class OutClass {
	private int id;
	private String name;

	public OutClass() {
	}

	public OutClass(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Inner1 getInner1() {
		return new Inner1();
	}

	private class Inner1 implements ForInner1 {
		private String info; // 自己的成员

		@Override
		public void printSomething() {
			info = "hello";
			System.out.println(id + "-" + name + "-" + info);
			// 通过Outer.this返回其外部类引用
			System.out.println(OutClass.this);
		}
	}

	public class Inner2 {
		public void printMe() {
			System.out.println(this);
		}
	}
}

public class NormalNestedClassTest {
	public static void main(String[] args) {
		OutClass outClass = new OutClass(10, "boy");
		// 错误 Inner1不可见
		// OutClass.Inner1 in1 = outClass.getInner1();
		// 错误 Inner1不可见
		// outClass.getInner1().printSomething();
		// 可通过接口访问
		ForInner1 inner1 = outClass.getInner1();
		inner1.printSomething();

		OutClass.Inner2 inner2 = outClass.new Inner2();
		inner2.printMe();
	}
}
