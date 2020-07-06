package com.duyj2.work.patterns.decorator;

/*
 * 通过使用修饰模式，可以在运行时扩充一个类的功能。
 * 原理是：增加一个修饰类包裹原来的类，包裹的方式一般是通过在将原来的对象作为修饰类的构造函数的参数。
 * 装饰类实现新的功能，但是，在不需要用到新功能的地方，它可以直接调用原来的类中的方法。
 * 修饰类必须和原来的类有相同的接口。
 * 修饰模式是类继承的另外一种选择。类继承在编译时候增加行为，而装饰模式是在运行时增加行为。
 * 
 * 喝咖啡的时候，可以加糖、冰、牛奶等好多原料，所以可以有好多种排列组合，使用装饰者模式可以减少需要类的数量，也可以动态增加类型
 *
 */

public class TestDecorator {

	public static void main(String[] args) {
		
		//普通的树
		ITree tree = new Tree();
		tree.show();

        System.out.println("-----------------------");

		// 装饰成一个苹果树
		ITree appleTree = new DecoratorApple(tree);
		appleTree.show();

		System.out.println("-----------------------");
		
		// 装饰一个覆盖了雪的苹果树
		ITree snowAppleTree = new DecoratorApple(new DecoratorSnow(new Tree()));
		snowAppleTree.show();

        System.out.println("-----------------------");
		
		// 装饰成一个开花的覆盖了雪的苹果树
		ITree christmasTree = new DecoratorFlower(snowAppleTree);
		christmasTree.show();
	}
}


