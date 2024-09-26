package com.lzl.thread.abc;


import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author eren.liao
 * @version v1.0
 * @description
 * @date 2024/9/4 19:04
 **/
public class Test {


    public static void main(String[] args) {


        String simpleName = Integer.class.getSimpleName();
        String b = BigDecimal.class.getSimpleName();
        String name = Integer.class.getName();

        String typeName = Integer.class.getTypeName();

        System.out.println(b);
        System.out.println(simpleName);
        System.out.println(name);
        System.out.println(typeName);

        String[] strings = {"1", "2", "3"};

        System.out.println(Arrays.toString(strings));

        String squareMeter = "\u33A1";
        System.out.println("面积：" + squareMeter + "m²");
    }
}

    
    
    