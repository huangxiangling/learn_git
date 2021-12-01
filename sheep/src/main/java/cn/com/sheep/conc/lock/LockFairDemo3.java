package cn.com.sheep.conc.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairDemo3 {

    private static int race = 0;
    private static ReentrantLock lock = new ReentrantLock(false);
    private static CountDownLatch downLatch = new CountDownLatch(0);

    public static void increase() {
        lock.lock();
        race++;
        lock.unlock();
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        long now = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    downLatch.countDown();
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
        }

        System.out.println("downLatch.getCount() == " + downLatch.getCount());
        executorService.shutdown();
        try {
            downLatch.await();
            System.out.println("主线程已经执行完了。。。。。。");
            System.out.println("========= > " + race);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
