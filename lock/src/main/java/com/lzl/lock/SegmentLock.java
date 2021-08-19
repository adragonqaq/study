package com.lzl.lock;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author: liaozhilong
 * @date: 2021/8/19 10:02
 * @Description: 分段锁
 * key锁（要保证key的hashCode不变,否则无法释放锁。即加锁之后不要手动更改lockMap）
 */
public class SegmentLock<T> {
    //默认分段数量
    private Integer segments = 16;
    private final HashMap<Integer, ReentrantLock> lockMap = new HashMap<>();
    public SegmentLock() {
        init(null, false);
    }
    public SegmentLock(Integer counts, boolean fair) {
        init(counts, fair);
    }
    private void init(Integer counts, boolean fair) {
        if (counts != null) {
            segments = counts;
        }
        for (int i = 0; i < segments; i++) {
            lockMap.put(i, new ReentrantLock(fair));
        }
    }
    public void lock(T key) {
        ReentrantLock lock = lockMap.get(key.hashCode() % segments);
        lock.lock();
    }
    public void unlock(T key) {
        ReentrantLock lock = lockMap.get(key.hashCode() % segments);
        lock.unlock();
    }
}