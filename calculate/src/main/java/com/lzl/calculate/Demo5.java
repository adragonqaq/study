package com.lzl.calculate;

/**
 * <p>
 * <b>创建日期：</b> 2021/10/22
 * </p>
 *
 * @author liaozhilong
 *
 *         << 左移运算符，将运算符左边的对象向左移动运算符右边指定的位数（在低位补0） x<<3 >> "有符号"右移运算
 *         符，将运算符左边的对象向右移动运算符右边指定的位数。使用符号扩展机制，也就是说，如果值为正，则在高位补0，如果值为负，则在高位补1. x>>3 >>> "无符号"右移运算
 *         符，将运算符左边的对象向右移动运算符右边指定的位数。采用0扩展机制，也就是说，无论值的正负，都在高位补0. x>>>3
 */
public class Demo5 {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(6297));
        System.out.println(Integer.toBinaryString(-6297));
        System.out.println(Integer.toBinaryString(6297 >> 5));
        System.out.println(Integer.toBinaryString(-6297 >> 5));
        System.out.println(Integer.toBinaryString(6297 >>> 5));
        System.out.println(Integer.toBinaryString(-6297 >>> 5));
        System.out.println(Integer.toBinaryString(6297 << 5));
        System.out.println(Integer.toBinaryString(-6297 << 5));

        /**
         * x<<y 相当于 x*2y ；x>>y相当于x/2y 从计算速度上讲，移位运算要比算术运算快。 如果x是负数，那么x>>>3没有什么算术意义，只有逻辑意义。
         */
    }
}
