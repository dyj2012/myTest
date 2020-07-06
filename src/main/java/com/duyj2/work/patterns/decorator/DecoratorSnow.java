package com.duyj2.work.patterns.decorator;

public class DecoratorSnow implements ITree {

	private ITree tree;

	public DecoratorSnow(ITree tree) {
		this.tree = tree;
	}

	public void show() {
		tree.show();
		System.out.println("I have snow.");
	}
}
