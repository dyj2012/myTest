package com.duyj.work.algorithm.sort;

//每次遍历 选择最小的，交换到前面
public class Select extends Sorter {

    @Override
    public int[] sort(int[] num) {
        int min;
        for (int i = 0; i < num.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[j] < num[min]) {
                    min = j;
                }
            }
            if (i != min) {
                swap(num, i, min);
            }
        }
        return num;
    }

}
