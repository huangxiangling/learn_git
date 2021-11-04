package cn.com.sheep.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTest {

    // 获得 acquire
    // 释放 release

    private static int count;
    static final Semaphore se = new Semaphore(1);
    static final Lock lock = new ReentrantLock();

    private static void addOne(){
        // 用信号量保证互斥
        lock.lock();
        try {
            //se.acquire();
            count += 1;
        } finally {
            //se.release();
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            count = 20;
        });

        Thread thread2 = new Thread(() -> {
            thread1.start();

        });

        thread2.start();
        try {
            thread1.join();
            thread2.join();
            System.out.println("==="+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

