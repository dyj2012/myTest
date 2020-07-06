package com.duyj2.work.algorithm.sort;

public class SorterTest {

    private static void print(int[] num) {
        for (int a : num) {
            System.out.print(a + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int num[] = {67, 69, 75, 87, 38, 90, 99, 10, 11, 56, 23, 7, 2, 89, 65, 56, 90, 28, 73, 47};
        print(num);

        print(new Bubble().sort(num.clone()));

        print(new Select().sort(num.clone()));

        print(new Quick().sort(num.clone()));

        print(new Insert().sort(num.clone()));

        print(new Shell().sort(num.clone()));

    }
}
