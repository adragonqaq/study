package com.lzl.thread.threadLocal.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaozhilong
 * @date 2021/8/20 18:11
 * @Description
 */
class ThreadLocalMemory {
    // Thread local variable containing each thread's ID
    public ThreadLocal<List<Object>> threadId = new ThreadLocal<List<Object>>() {
        @Override
        protected List<Object> initialValue() {
            List<Object> list = new ArrayList<Object>();
            for (int i = 0; i < 10000; i++) {
                list.add(String.valueOf(i));
            }
            return list;
        }
    };
    // Returns the current thread's unique ID, assigning it if necessary
    public List<Object> get() {
        return threadId.get();
    }
    // remove currentid
    public void remove() {
        threadId.remove();
    }




    public static void main(String[] args)
            throws InterruptedException {

        //  为了复现key被回收的场景，我们使用临时变量
        ThreadLocalMemory memeory = new ThreadLocalMemory();

        // 调用
//        incrementSameThreadId(memeory);

        System.out.println("GC前：key:" + memeory.threadId);
//        System.out.println("GC前：value-size:" + refelectThreadLocals(Thread.currentThread()));

        // 设置为null，调用gc并不一定触发垃圾回收，但是可以通过java提供的一些工具进行手工触发gc回收。
        memeory.threadId = null;
        System.gc();

        System.out.println("GC后：key:" + memeory.threadId);
//        System.out.println("GC后：value-size:" + refelectThreadLocals(Thread.currentThread()));

        // 模拟线程一直运行
        while (true) {
        }
    }
}
