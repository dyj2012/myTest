package com.duyj.work.algorithm.sort;

public abstract class Sorter {

    public void swap(int[] num, int a, int b) {
        int t = num[a];
        num[a] = num[b];
        num[b] = t;
    }

    public abstract int[] sort(int[] num);
}
