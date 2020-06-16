package com.duyj.work.algorithm.sort;

//每次遍历 将相邻两数中大的 交换到右侧
public class Bubble extends Sorter {

    @Override
    public int[] sort(int[] num) {
        int count = num.length;
        for (int i = count - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (num[j] > num[j + 1]) {
                    swap(num, j, j + 1);
                }
            }
        }
        return num;
    }
}
