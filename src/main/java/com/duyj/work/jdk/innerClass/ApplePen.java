package com.duyj.work.jdk.innerClass;

class Apple {
	protected String name = "apple";
	{System.out.println("I have an apple");}
}

class Pen {
	protected String name = "pen";

	{
		System.out.println("I have a pen");
	}
}

public class ApplePen {

	public static void main(String[] args) {
		ApplePen ap = new ApplePen();
		ap.haha();
	}

	public void haha() {
		C1 c1 = new C1();
		C2 c2 = new C2();
		System.out.println(c1.name + "-" + c2.name);
	}

	private class C1 extends Apple {
	}

	private class C2 extends Pen {
	}

}
