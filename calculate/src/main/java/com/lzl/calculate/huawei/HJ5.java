package com.lzl.calculate.huawei;


import java.util.Scanner;

/**
 * 进制转换
 *
 * 描述
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 * 输入描述：
 * 输入一个十六进制的数值字符串。注意：一个用例会同时有多组输入数据，请参考帖子https://www.nowcoder.com/discuss/276处理多组输入的问题。
 *
 * 输出描述：
 * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
 */
public class HJ5 {


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String s = in.nextLine();    //读入数字

            int count = 0;    //记录转换后的数字

            for(int i=0; i < s.length()-2; i++){
                //由于前面两位是'0x'，故从第三位开始
                char tc = s.charAt(i+2);
                int t = 0;    //记录字母转换成的数值

                //将字母转换为数值
                if(tc>='0' && tc<='9')
                    t = tc - '0';
                    //字母'A'/'a'~'F''f'对应数字10~15
                else if(tc>='A' && tc<='F')
                    t = tc - 'A' + 10;
                else if(tc>='a' && tc<='f')
                    t = tc - 'a' +10;
                //计算加和
                count += t * Math.pow(16, s.length()-i-3);
            }
            System.out.println(count);
        }
    }


}
