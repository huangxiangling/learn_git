package cn.com.sheep.conc.current.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class CyclicBarrierDemo2 {
    public static void main(String[] args) {
        int n = 4;
        CyclicBarrier barrier = new CyclicBarrier(n);

        IntStream.range(0, n).forEach(i -> {

            new Writer(barrier).start();

        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("====> CyclicBarrier重用");

        IntStream.range(0, n).forEach(i -> {

            new Writer(barrier).start();

        });
    }

    static class Writer extends Thread {

        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {

            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");

            try {
                // 以睡眠来模拟写入数据操作
                Thread.sleep(3000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "所有线程写入完毕，继续处理其他任务...");

        }
    }
}
