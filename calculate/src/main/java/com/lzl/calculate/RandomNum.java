package com.lzl.calculate;

import java.util.Random;

/**
 * 获取随机数
 */
public class RandomNum {

    public static void main(String[] args) {

        Random rn = new Random();
        int answer = rn.nextInt(10) + 1;
        System.out.println(answer);

//        [0.0,1.0）
        int ran2 = (int) (Math.random()*10+1);
        System.out.println(ran2);




    }
}
