package cn.com.sheep.conc.lock;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private  Object[] items = new Object[20];
    int putP, takeP, count;

    public void put(Object x) {

        lock.lock();
        try {
            // 当count等于数组大小时，当前线程等待，知道notFull通知，再进行生产
            while (count == items.length) {
                notFull.await();
            }
            items[putP] = x;
            if (++putP == items.length) {
                putP = 0;
            }
            ++count;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            // 当count为0时，进入等待，直到notEmpty通知，进行消费
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[takeP];
            if (++takeP == items.length) {
                takeP = 0;
            }
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {




//        AtomicInteger a = new AtomicInteger();
//        new Thread(() -> {
//            a.set(add());
//        }).start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(a.get());


//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        Future<Integer> future = executorService.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return add();
//            }
//        });
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(future.get());


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
        System.out.println(future.get());
    }


    public static int add(){
        int sum = 0;
        for(int i = 0; i < 100; i++){
            sum++;
        }
        return sum;
    }
}
