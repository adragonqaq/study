package com.lzl.jvm;

import java.nio.ByteBuffer;

/**
 * -XX:MaxDirectMemorySize（重点）
 * 内存不足时抛出OutOfMemoryError或OutOfMemoryError：Direct buffer memory。
 * 如果使用Java自带的 ByteBuffer.allocateDirect(size) 或者直接 new DirectByteBuffer(capacity) , 这样受-XX:MaxDirectMemorySize 这个JVM参数的限制. 其实底层都是用的Unsafe#allocateMemory,区别是对大小做了限制. 如果超出限制直接OOM.
 *
 *
 * 如果通过反射的方式拿到Unsafe的实例,然后用Unsafe的allocateMemory方法分配堆外内存. 确实不受-XX:MaxDirectMemorySize这个JVM参数的限制 . 所以限制的内存大小为操作系统的内存.
 *
 * 如果不设置-XX:MaxDirectMemorySize 默认的话,是跟堆内存大小保持一致. [堆内存大小如果不设置的话,默认为操作系统的 1/4, 所以 DirectMemory的大小限制JVM的Runtime.getRuntime().maxMemory()内存大小 . ]
 *
 * 看深入理解java虚拟机的"本机直接内存溢出"一节中，容易误认为-XX:MaxDirectMemorySize这个参数对Unsafe的allocateMemory方法也有效，事实上并不是，这个参数只对Java自带的 ByteBuffer.allocateDirect(size) 或者直接 new DirectByteBuffer(capacity) 才有效。
 *
 * Unsafe的allocateMemory方法限制的内存大小为操作系统的内存.
 * ————————————————
 *
 * 原文链接：https://blog.csdn.net/qq_34626094/article/details/117173323
 *
 *
 *
 *来自
 *用JDK8的一定要配置：-Xms -Xmx -XX:MaxDirectMemorySize，【Xmx +（加） MaxDirectMemorySize】的值不能超过docker的最大内存，不然docker内存占满了会被oomkill掉；
 * https://www.cnblogs.com/dengq/p/13687423.html
 * @author eren.liao
 * @date 2023/2/9 10:46
 */
public class MaxDirectMemorySizeTest {

    public static void main(String[] args) {

        // 得到的是byte
        // long l = Runtime.getRuntime().maxMemory();
        //1G=1024MB，1MB=1024KB，1KB=1024   1G = 1073741824字节
        // 1byte = 8bit
        //“1byte等于8bit。数据存储是以“字节”（Byte）为单位，数据传输大多是以“位”（bit）为单位，一个位就代表一个0或1（即二进制），每8个位（bit）组成一个字节（Byte），是最小一级的信息单位
        // System.out.println(l);


        // 测试 fullgc
        new Thread(() -> {
            try {
                for (; ;) {
                    System.out.println("增加堆外内存");
                    test();
                    //休眠5秒
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * vm options
     *
     * -Xms1024m -Xmx1024m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=64m
     */
    public static void test(){
        // 12Mb
        ByteBuffer.allocateDirect(12*1024*1024);
        // System.out.println(Runtime.getRuntime().maxMemory());
    }

}
