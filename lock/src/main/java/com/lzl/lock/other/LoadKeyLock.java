package com.lzl.lock.other;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author: liaozhilong
 * @date: 2021/8/19 10:03
 * @Description: 基于Reentrant Lock 封装的分段锁，使得锁粒度细化到 -> Key级别（慎重该工具类）
 */
public class LoadKeyLock {

    /**
     * 分段数量（锁数量）（要求2的指数幂）
     */
    private int segments = 16;

    private final HashMap<Integer, ReentrantLock> lockMap = new HashMap<>(segments);

    public LoadKeyLock(boolean fair) {
        for (int i = 0; i < segments; i++) {
            lockMap.put(i, new ReentrantLock(fair));
        }
    }


    /**
     * 注意!!!,调用此方法，该方法封装了lock，没紧跟try，故调用者要在调用该方法时要紧跟try！！！！且在finally中解锁
     */
    public void lock(long key) {
        int hash = (int) (key & (segments - 1));
        ReentrantLock lock = lockMap.get(hash);
        lock.lock();
    }


    public void unLock(long key) {
        int hash = (int) (key & (segments - 1));
        ReentrantLock lock = lockMap.get(hash);
        lock.unlock();
    }

    /**
     * 注意!!!,调用此方法，该方法封装了lock，没紧跟try，故调用者要在调用该方法时要紧跟try！！！！且在finally中解锁
     */
    public boolean tryLock(long key) {
        int hash = (int) (key & (segments - 1));
        ReentrantLock lock = lockMap.get(hash);
        return lock.tryLock();
    }


}