package com.duyj.work.patterns.decorator;

public class DecoratorApple implements ITree {

	private ITree tree;

	public DecoratorApple(ITree tree) {
		this.tree = tree;
	}

	public void show() {
		tree.show();
		System.out.println("I have apple.");
	}

}
