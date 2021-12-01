package cn.com.sheep.conc.current.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapDemo {


    public static void demo1() {
        final Map<String, AtomicInteger> count = new ConcurrentHashMap<>();
        final CountDownLatch endLath = new CountDownLatch(2);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                AtomicInteger oldValue;
                for (int i = 0; i < 5; i++) {
                    oldValue = count.get("a");
                    if (null == oldValue) {
                        AtomicInteger zeroValue = new AtomicInteger(0);
                        // putIfAbsent 如果已经存在key为 a 的value，则不添加
                        oldValue = count.putIfAbsent("a", zeroValue);
                        if (null == oldValue) {
                            oldValue = zeroValue;
                        }
                    }
                    // 以原子方式将当前值递增 1。
                    // 返回：
                    // 更新后的值
                    oldValue.incrementAndGet();
                }
                endLath.countDown();
            }
        };

        new Thread(task).start();
        new Thread(task).start();

        try {
            // 如果当前计数大于零，则当前线程出于线程调度而被禁用并处于休眠状态
            endLath.await();
            System.out.println("========>" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        demo1();

    }
}
