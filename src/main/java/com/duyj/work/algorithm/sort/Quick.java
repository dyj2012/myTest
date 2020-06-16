package com.duyj.work.algorithm.sort;

// 将比基准小的数 和 比基准大的数 交换位置，
// 分治递归执行

public class Quick extends Sorter {

    private int division(int[] num, int left, int right) {
        // 以最左边的数(left)为基准
        int base = num[left];
        while (left < right) {
            // 从右向左遍历，找出小于base的数，放在左边
            while (left < right && num[right] >= base)
                right--;
            num[left] = num[right];

            // 从左向右遍历，找到大于base的数，放在右边
            while (left < right && num[left] <= base)
                left++;
            num[right] = num[left];
        }

        // 此时left等于right， left左侧的数都比base小，left右侧的数都比base大
        // 最后将base放到left位置。
        num[left] = base;
        return left;
    }

    private void quickSort(int[] num, int left, int right) {
        if (left < right) {

            int base = division(num, left, right);

            //分治左边部分
            quickSort(num, left, base - 1);

            //分治右边部分
            quickSort(num, base + 1, right);
        }
    }


    @Override
    public int[] sort(int[] num) {

        quickSort(num, 0, num.length - 1);

        return num;
    }
}
