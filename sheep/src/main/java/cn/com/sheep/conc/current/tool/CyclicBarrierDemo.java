package cn.com.sheep.conc.current.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("回调>>" + Thread.currentThread().getName());
                System.out.println("回调>>线程组执行结束");
                System.out.println("==>各个子线程执行结束。。。。");
            }
        });

        IntStream.range(0, 5).forEach(i -> {
            new Thread(new ReadNum(i, cyclicBarrier)).start();
        });

    }

    static class ReadNum implements Runnable {

        private int id;
        private CyclicBarrier cyc;

        public ReadNum(int id, CyclicBarrier cyc) {
            this.id = id;
            this.cyc = cyc;
        }

        @Override
        public void run() {

            System.out.println("id:" + id + "," + Thread.currentThread().getName());
            try {
                cyc.await();
                System.out.println("线程组任务" + id + "结束，其他任务继续");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
//            synchronized (this) {
//
//            }
        }
    }


}
