package com.lzl.calculate.huawei;

import java.util.Scanner;

/**
 *
 * 计算某字母出现次数
 *
 *
 * 描述 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字母，然后输出输入字符串中该字母的出现次数。不区分大小写，字符串长度小于500。
 *
 * 输入描述： 第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字母。
 *
 * 输出描述： 输出输入字符串中含有该字符的个数。
 */
public class HJ2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase();
        String s = sc.nextLine();
        System.out.print(str.length() - str.replaceAll(s, "").length());

    }
}
