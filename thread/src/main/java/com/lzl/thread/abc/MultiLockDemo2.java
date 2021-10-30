package com.lzl.thread.abc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 上面这个版本存在线程空跑 for 循环占用 CPU 的情况，通过线程的等待通知机制优化成如下版本
 *
 * Condition的执行方式，是当在线程Consumer中调用await方法后，线程Consumer将释放锁，并且将自己沉睡，等待唤醒，
 * 线程Producer获取到锁后，开始做事，完毕后，调用Condition的signalall方法，唤醒线程Consumer，线程Consumer恢复执行。
 */
public class MultiLockDemo2 {

    /**
     * 使用公平锁，防止一个线程连续获取锁的情况
     */
    private Lock lock = new ReentrantLock();
    // 计数
    private int COUNT = 0;

    // 管程模型中的条件变量
    private Condition isTarget = lock.newCondition();

    private void printChar(int threadIdentify) {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                /**
                 * threadIdentify: 0 表示线程 a, 1 表示线程 b, 2 表示线程 c
                 */
                while (COUNT % 3 != threadIdentify) {
                    try {
                        // 调用 await 之后会释放锁资源，让出 CPU 时间片
                        isTarget.await();
                        // 这里即使唤醒之后会有多个线程，都需要到管程入口队列中获取对应的锁才能继续往下执行，
                        // 只是这里的执行点不是 printChar 方法的入口，而是执行 await() 之后的代码
                        // 管程是一个解决并发问题的模型，对管程有疑问的话，可以去网上搜索资料
                        // 加锁的这段临界区代码永远只有一个线程可以访问

                        // 线程被唤醒以后继续在此处往下执行，所以这个地方用 while 循环，
                        // 防止 COUNT % 3 != threadIdentify 这个条件不满足了
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(Thread.currentThread().getName());
                COUNT++;

                // 这里通知完，仅是从管程的「条件等待队列」中进入到「入口等待队列」去重新获取到锁资源然后继续执行，
                // 这也是  while (COUNT % 3 != threadIdentify) 这里为什么用 while 循环的根本原因。
                // 当被唤醒的线程在「入口等待队列」获取锁然后执行 await() 后面的代码时，有可能曾经满足的条件，
                // 此刻已经不满足了，所以需要循环方式检验条件变量
                isTarget.signalAll();
            } finally {
                lock.unlock();
            }
            System.out.print(i);
        }
    }

    public static void main(String[] args) {
        MultiLockDemo2 lockDemo = new MultiLockDemo2();
        Thread a = new Thread(() -> lockDemo.printChar(0), "A");
        Thread b = new Thread(() -> lockDemo.printChar(1), "B");
        Thread c = new Thread(() -> lockDemo.printChar(2), "C");

        c.start();
        b.start();
        a.start();
    }

}
