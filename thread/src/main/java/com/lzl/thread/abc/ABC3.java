package com.lzl.thread.abc;


import java.util.concurrent.Semaphore;

/**
 * 使用信号量也可以, 这个思路最简单, 整个代码也比较简洁
 *
 * 注意:
 *
 * lock是需要lock所有者去释放的, 即谁lock, 谁释放, 不可以跨线程, 会报java.lang.IllegalMonitorStateException;
 *
 * semaphore是没有所有者的说法, 可以跨线程释放和获取.
 *
 * Semaphore的主要方法摘要：
 *
 * 　　void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
 *
 * 　　void release():释放一个许可，将其返回给信号量。
 *
 * 　　int availablePermits():返回此信号量中当前可用的许可数。
 *
 * 　　boolean hasQueuedThreads():查询是否有线程正在等待获取。
 */
public class ABC3 {
    private static Semaphore A = new Semaphore(1);//这里表示只能有一个线程走
    private static Semaphore B = new Semaphore(1);
    private static Semaphore C = new Semaphore(1);

    static class ThreadA extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    A.acquire();
                    System.out.print("A");
                    B.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class ThreadB extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    B.acquire();
                    System.out.print("B");
                    C.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class ThreadC extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    C.acquire();
                    System.out.println("C");
                    A.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        B.acquire(); C.acquire(); // 开始只有A可以获取, BC都不可以获取, 保证了A最先执行
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
