package com.lzl.calculate;

/**
 * <p>
 * <b>创建日期：</b> 2021/10/22
 * </p>
 *
 * @author liaozhilong
 *         <p>
 *         或运算符用符号“|”表示，其运算规律如下： 两个位只要有一个为1，那么结果就是1，否则就为0
 */
public class Demo2 {

    public static void main(String[] args) {
        int a = 129;
        int b = 128;
        System.out.println("a 和b 或的结果是：" + (a | b));
    }

    /**
     * a 的值是129，转换成二进制就是10000001，而b 的值是128，转换成二进制就是10000000，根据或运算符的运算规律，只有两个位有一个是1，结果才是1，可以知道结果就是10000001，即129
     */
}
