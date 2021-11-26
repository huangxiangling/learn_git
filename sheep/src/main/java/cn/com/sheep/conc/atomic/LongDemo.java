package cn.com.sheep.conc.atomic;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongDemo {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();
        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {

                for (int j = 0; j < 10000; j++) {
                    atomicLong.getAndIncrement();
                    longAdder.increment();
                }

            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Executors.newFixedThreadPool(1);
        System.out.println("atomicLong = " + atomicLong.get());
        System.out.println("longAdder = " + longAdder.sum());
    }
}
