package com.duyj2.work.jdk.innerClass;

//内部类 接口
interface Iinner {
	void printSomething();
}

// 匿名内部类 父类
class Finner {
	String name;
	Finner() {
	}
	Finner(String name) {
		this.name = name;
	}
	public void printSomething() {
		System.out.println(this + " name:" + name);
	}
}

class Outer {

	// 匿名内部类1 实现接口，然后返回
	public Iinner getInner1() {
		return new Iinner() {
			@Override
			public void printSomething() {
				System.out.println("同一接口的第一种实现");
			}
		};
	}
	
	//或者 实现接口而后调用  
	//等同于父类不实现，交由匿名内部类实现，这样可限制父类的类型
	//另一个意义 可实现同一个接口的不同实现
	public void printThing(){
		new Iinner(){
			@Override
			public void printSomething() {
				System.out.println("同一接口的第二种实现");
			}
		}.printSomething();
	}

	// 匿名内部类2  继承一个父类
	// 可通过父类构造函数传递参数到匿名内部类内部
	public Finner getInner2(String name) {
		Finner a= new Finner(name) {
			//隐秘的复写了父类的方法
			@Override
			public void printSomething() {
				System.out.println("I am "+name);
			}
			public void say(){
				//无法被调用
			}
		};
		return a;
	}

	// 匿名内部类3 直接传参到匿名内部类内部 参数为final
	public Finner getInner3(final String str) {
		return new Finner() {
			private String s = str;
			public void printSomething() {
				System.out.println(s);
			}
		};
	}

}

public class NoNameInnerTest {
	public static void main(String[] args) {
		Outer out = new Outer();
		out.getInner1().printSomething();
		out.printThing();
		
		out.getInner2("tom").printSomething();
		out.getInner3("hehe").printSomething();
		
	}
}
