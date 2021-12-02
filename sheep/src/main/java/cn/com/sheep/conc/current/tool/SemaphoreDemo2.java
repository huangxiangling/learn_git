package cn.com.sheep.conc.current.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreDemo2 {

    private final static int threadCount = 20;

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(5);
        IntStream.range(0, threadCount).forEach(i -> {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    // 获取全部许可，退化成串行执行
                    semaphore.acquire(3);
                    test(threadNum);
                    // 释放多个许可
                    semaphore.release(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        System.out.println("id:" + threadNum + "," + Thread.currentThread().getName());
        Thread.sleep(1000);
    }

}
