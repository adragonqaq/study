package com.lzl.thread.threadLocal;

/**
 * @author liaozhilong
 * @date 2021/8/20 9:34
 * @Description 认识ThreadLocal
 */
public class MyThreadLocal {

    public static void main(String[] args) {

        incrementSameThreadId();
        new Thread(()->{
            incrementSameThreadId();
        }).start();

        new Thread(()->{
            incrementSameThreadId();
        }).start();

    }

    private static void incrementSameThreadId(){
        try {
            for(int i = 0;i< 5;i++){
                System.out.println(Thread.currentThread()+"_"+ i +",threadId:"+ThreadLocalId.get());
            }
        }finally {
            ThreadLocalId.remove();
        }
    }
}
