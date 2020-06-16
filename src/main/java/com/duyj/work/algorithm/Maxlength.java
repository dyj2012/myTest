package com.duyj.work.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 一串有正有负的整数序列，找出其中和最大的子序列
 */

public class Maxlength {

    private static final int N = 20;

    private static void doMath(int[] a) {
        //算法开始
        //一、从左到右累加序列，放在数组b中
        int sum = 0;
        int[] b = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            b[i] = sum;
            sum += a[i - 1];
        }
        //System.out.println(Arrays.toString(b));

        //二、从右到左累加序列，放在数组c中
        sum = 0;
        int[] c = new int[N + 1];
        for (int i = N - 1; i >= 0; i--) {
            sum += a[i];
            c[i] = sum;
        }
        c[N] = 0;
        //System.out.println(Arrays.toString(c));

        //遍历所有组合，找到最大值
        int ii = 0;
        int jj = N;
        for (int i = 0; i < N + 1; i++) {
            for (int j = N; j >= i; j--) {
                int ss = b[i] + c[j];
                if (ss <= sum) {
                    sum = ss;
                    ii = i;
                    jj = j - 1;
                }
            }
        }
        System.out.println("下标从 " + ii + " 到 " + jj + " 为最大子序列， 最大值：" + (c[0] - sum));
        System.out.println();
    }

    public static void main(String[] args) {
        //测试开始
        int[] a = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = i;
        }
        System.out.println(Arrays.toString(a));
        doMath(a);

        for (int i = 0; i < N; i++) {
            a[i] = i - 10;
        }
        System.out.println(Arrays.toString(a));
        doMath(a);

        for (int i = 0; i < N; i++) {
            a[i] = 10 - i;
        }
        System.out.println(Arrays.toString(a));
        doMath(a);

        for (int i = 0; i < N; i++) {
            a[i] = 5 - Math.abs(10 - i);
        }
        System.out.println(Arrays.toString(a));
        doMath(a);

        //随机生成一个序列
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            a[i] = r.nextInt(N) - 10;
        }
        System.out.println(Arrays.toString(a));
        doMath(a);

    }
}


//去掉两端最小值，剩余中间的即为最大值
