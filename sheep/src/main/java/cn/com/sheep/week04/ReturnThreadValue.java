package cn.com.sheep.week04;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ReturnThreadValue {

    private static AtomicInteger sum = new AtomicInteger();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ret1();
        ret2();
        ret3();
    }

    public static void ret1() {
        AtomicInteger a = new AtomicInteger();
        new Thread(() -> {
            a.set(add());
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ret1 sum:" + a.get());

    }

    public static void ret2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return add();
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ret2 sum:" + future.get());
    }

    public static void ret3() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1024));
        Future<Object> future = executor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return add();
            }
        });
        Thread.sleep(1000);
        System.out.println("ret3 sum:" + future.get());
    }

    public static int add() {
        for (int i = 0; i < 10000; i++) {
            sum.getAndIncrement();
        }
        return sum.get();
    }
}
