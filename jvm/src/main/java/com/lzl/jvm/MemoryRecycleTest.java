package com.lzl.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eren.liao
 * @date 2023/2/8 10:19
 */
public class MemoryRecycleTest {
    static volatile List<OOMobject> list = new ArrayList<>();
    public static void main(String[] args) {
        //指定要生产的对象大小为512M
        int count = 512;
        //新建一条线程,负责生产对象
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(String.format("第%s次生产%s大小的对象", i, count));
                    addObject(list, count);
                    //休眠40秒
                    Thread.sleep(i * 10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //新建一条线程,负责清理List,回收JVM内存
        new Thread(() -> {
            for (; ; ) {
                //当List内存到达512M,就通知GC回收堆
                if (list.size() >= count) {
                    System.out.println("清理list.... 回收jvm内存....");
                    list.clear();
                    //通知GC回收
                    System.gc();
                    //打印堆内存信息
                    printJvmMemoryInfo();
                }
            }
        }).start();

        //阻止程序退出
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addObject(List<OOMobject> list, int count) {
        for (int i = 0; i < count; i++) {
            OOMobject ooMobject = new OOMobject();
            //向List添加一个1M的对象
            list.add(ooMobject);
            try {
                //休眠100毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class OOMobject {
        //生成1M的对象
        private byte[] bytes = new byte[1024 * 1024];
    }

    public static void printJvmMemoryInfo() {
        //虚拟机级内存情况查询
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        vmTotal = rt.totalMemory() / byteToMb;
        vmFree = rt.freeMemory() / byteToMb;
        vmMax = rt.maxMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        System.out.println("");
        System.out.println("JVM内存已用的空间为：" + vmUse + " MB");
        System.out.println("JVM内存的空闲空间为：" + vmFree + " MB");
        System.out.println("JVM总内存空间为：" + vmTotal + " MB");
        System.out.println("JVM总内存最大堆空间为：" + vmMax + " MB");
        System.out.println("");
    }
}
