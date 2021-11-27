package cn.com.sheep.conc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairDemo {

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

    /**
     * Thread.activeCount()  返回当前线程的线程组及其子组中活动线程数的估计值
     * 手动创建多个线程执行一段代码，使用Thread.activeCount()判断手动创建的活动线程是否都执行完任务释放后，获取正确的结果
     */
    public static void run() {
        // 当前线程组的估计数据
        int count = Thread.activeCount();
        long now = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        System.out.println("======> 目前程序运行的线程组个数；" + Thread.activeCount());
        while (Thread.activeCount() > count) {
            // 这里表示当前主线程放弃CPU调度，等到while条件不满足时，当前线程才会继续执行后面的代码
            Thread.yield();
        }
        System.out.println(lock.getClass().getName() + "ts = " + (System.currentTimeMillis() - now));
        System.out.println("=====>" + race);
    }
}
