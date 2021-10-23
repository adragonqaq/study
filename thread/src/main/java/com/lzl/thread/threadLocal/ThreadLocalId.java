package com.lzl.thread.threadLocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liaozhilong
 * @date 2021/8/20 10:13
 * @Description 自定义的ThreadLocal管理类
 */
public class ThreadLocalId {


    private static final AtomicInteger nextId  = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int get(){
        return threadId.get();
    }

    public static void remove(){
        threadId.remove();
    }

}
