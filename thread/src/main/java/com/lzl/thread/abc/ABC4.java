package com.lzl.thread.abc;

import java.util.concurrent.CyclicBarrier;

/**
 * 让多线程之间按照顺序输出，那么可以考虑用锁控制对应顺序，B等待A释放锁，C等待B释放锁，我们的选择可以有CountDownLatch和CyclicBarrier，但是有循环的条件，所以应该选择可以重置的CyclicBarrier。
 * 之后的思考就是如何通过CyclicBarrier实现C执行完后再执行A。
 *
 *
 * CyclicBarrier 使用场景
 * 可以用于多线程计算数据，最后合并计算结果的场景
 *
 * CyclicBarrier 与 CountDownLatch 区别
 * CountDownLatch 是一次性的，CyclicBarrier 是可循环利用的
 * CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。CyclicBarrier 参与的线程职责是一样的。
 *
 *
 */
public class ABC4 {

    public static void main(String[] args) {


        final CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
        final CyclicBarrier cyclicBarrier3 = new CyclicBarrier(2);
        final CyclicBarrier cyclicBarrier4 = new CyclicBarrier(3);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        System.out.println("A");
                        //控制A执行完在执行
                        cyclicBarrier2.await();
                        //等待ABC都运行完
                        cyclicBarrier4.await();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        cyclicBarrier2.await();
                        System.out.println("B");
                        //控制B执行完在执行C
                        cyclicBarrier3.await();
                        //等待ABC都运行完
                        cyclicBarrier4.await();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        cyclicBarrier3.await();
                        System.out.println("C");
                        //等待ABC都运行完
                        cyclicBarrier4.await();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        threadC.start();
        threadB.start();
        threadA.start();


    }
}
