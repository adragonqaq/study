package com.lzl.thread.abc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有A,B,C三个线程, A线程输出A, B线程输出B, C线程输出C
 *
 * 要求, 同时启动三个线程, 按顺序输出ABC, 循环10次
 *
 * 这是一个多线程协同的问题, 本身多线程是没有执行顺序的, 顺序不一定, Java在concurrent里面提供了多线程同步的支持
 *
 * 使用ReentrantLock来解决, 还有个state整数用来判断轮到谁执行了
 */

public class ABC {
    private static Lock lock = new ReentrantLock();//通过JDK5中的锁来保证线程的访问的互斥
    private static int state = 0;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 0) {
                    System.out.print("A");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 1) {
                    System.out.print("B");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 2) {
                    System.out.print("C");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }

}
