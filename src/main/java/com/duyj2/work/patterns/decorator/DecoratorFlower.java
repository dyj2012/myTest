package com.duyj2.work.patterns.decorator;

public class DecoratorFlower implements ITree {

	private ITree tree;

	public DecoratorFlower(ITree tree) {
		this.tree = tree;
	}

	public void show() {
		tree.show();
		System.out.println("I have flower.");
	}
}
