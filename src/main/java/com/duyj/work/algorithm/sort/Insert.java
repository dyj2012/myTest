package com.duyj.work.algorithm.sort;

//从左到右遍历，碰到小的就一直换到前面，直到合适的地方，就像整理扑克牌时的操作
public class Insert extends Sorter {
    @Override
    public int[] sort(int[] num) {

        int target;

        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历1 - n-1
        for (int i = 1; i < num.length; i++) {
            int j = i;
            target = num[i];

            while (j > 0 && target < num[j - 1]) {
                num[j] = num[j - 1];
                j--;
            }

            num[j] = target;
        }

        return num;
    }
}
