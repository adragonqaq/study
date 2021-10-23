package com.lzl.calculate;

/**
 * <p>
 * <b>创建日期：</b> 2021/10/22
 * </p>
 *
 * @author liaozhilong
 */
public class Demo4 {
    public static void main(String[] args) {
        int a = 15;
        int b = 2;
        System.out.println("a 与 b 异或的结果是：" + (a ^ b));
    }
    /**
     * 分析上面的程序段：a 的值是15，转换成二进制为1111，而b 的值是2，转换成二进制为0010，根据异或的运算规律，可以得出其结果为1101 即13。
     */
}
