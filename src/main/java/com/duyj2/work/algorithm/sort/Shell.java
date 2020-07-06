package com.duyj2.work.algorithm.sort;

//插入排序的扩展，使用跨度将数据分组，对每组进行插入排序，再将跨度变小（分组变少），继续排序
public class Shell extends Sorter {
    @Override
    public int[] sort(int[] num) {
        for (int gap = num.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < num.length; i++) {
                int j = i;
                while (j - gap >= 0 && num[j] < num[j - gap]) {
                    //插入排序采用交换法
                    swap(num, j, j - gap);
                    j -= gap;
                }
            }
        }

        return num;
    }
}
