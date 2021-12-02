package cn.com.sheep.conc.current.tool;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        IntStream.range(0, 5).forEach(i -> {
            new Thread(new ReadNum(i, countDownLatch)).start();
        });
        // 注意跟CyclicBarrier不同，这里在主线程await
        countDownLatch.await();
        System.out.println("==>各个子线程执行结束。。。。");
        System.out.println("==>主线程执行结束。。。。");
    }

    static class ReadNum implements Runnable {

        private int id;
        private CountDownLatch latch;

        public ReadNum(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:" + id + "," + Thread.currentThread().getName());
                //latch.countDown();
                System.out.println("线程组任务" + id + "结束，其他任务继续");
                latch.countDown();
            }

        }
    }
}
