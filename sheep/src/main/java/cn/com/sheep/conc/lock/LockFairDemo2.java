package cn.com.sheep.conc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairDemo2 {


    private static int race = 0;
    private static ReentrantLock lock = new ReentrantLock(false);

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
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
        }
        // 启动有序关闭，其中执行先前提交的任务，但不会接受新任务
        executorService.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lock.getClass().getName() + "ts = " + (System.currentTimeMillis() - now));
        System.out.println("=====>" + race);
    }
}
