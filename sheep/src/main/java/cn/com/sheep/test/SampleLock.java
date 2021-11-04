package cn.com.sheep.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SampleLock {

    private final Lock rtl = new ReentrantLock();

    int value;

    public void addOne(){
        // 获取锁
        rtl.lock();
        try {
            value += 1;
        }finally {
            rtl.unlock();
        }
    }
}
