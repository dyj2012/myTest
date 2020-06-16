package com.duyj.work.apachecommon.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class UtilsTest {

    public static void main(String[] args) {

        // 1 合并两个数组
        String[] s1 = new String[]{"1", "2", "3"};
        String[] s2 = new String[]{"a", "b", "c"};
        String[] s = ArrayUtils.addAll(s1, s2);
        System.out.println(ArrayUtils.toString(s));

        //2 截取从from开始字符串
        System.out.println(StringUtils.substringAfter("SELECT * FROM PERSON ", "FRO"));

        //3 判断该字符串是不是为数字(0~9)组成，如果是，返回true 但该方法不识别有小数点和 请注意。
        System.out.println(StringUtils.isNumeric("454534"));

        //4.取得类名
        System.out.println(ClassUtils.getShortClassName(UtilsTest.class));

        //取得其包名
        System.out.println(ClassUtils.getPackageName(UtilsTest.class));

        //5.NumberUtils
        System.out.println(NumberUtils.toInt("6"));

        //6.五位的随机字母和数字
        System.out.println(RandomStringUtils.randomAlphanumeric(5));

        //StringUtils,判断是否是空格字符
        System.out.println(StringUtils.isBlank("   "));

        //将数组中的内容以,分隔
        System.out.println(StringUtils.join(s1, ","));

        //在右边加下字符,使之总长度为6
        System.out.println(StringUtils.rightPad("abc", 6, 'T'));

        //首字母大写
        System.out.println(StringUtils.capitalize("abc a bb 1d"));

        //删除所有空格
        System.out.println(StringUtils.deleteWhitespace("   ab  c  "));

        //判断是否包含这个字符
        System.out.println(StringUtils.contains("abc", "ba"));

        //左边两个字符
        System.out.println(StringUtils.left("abc", 2));

    }
}
