package com.duyj.work.patterns.memento;

/*
 * 备忘录模式又叫做快照模式(Snapshot Pattern)或Token模式，是对象的行为模式。
　　备忘录对象是一个用来存储另外一个对象内部状态的快照的对象。
备忘录模式的用意是在不破坏封装的条件下，将一个对象的状态捕捉(Capture)住，并外部化，存储起来，
从而可以在将来合适的时候把这个对象还原到存储起来的状态。备忘录模式常常与命令模式和迭代子模式一同使用。
 */
public class Test {

	public static void main(String[] args) {
		
		Originator o = new Originator();
		o.setData("data1");
		o.show();
		
		CareTaker c = new CareTaker();
		c.setMeme(o.getStates());
		
		o.setData("data2");
		o.show();
		
		o.setStates(c.getMeme());
		o.show();

	}

}
