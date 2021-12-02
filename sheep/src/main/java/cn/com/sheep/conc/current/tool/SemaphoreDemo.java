package cn.com.sheep.conc.current.tool;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreDemo {

    public static void main(String[] args) {

        int n = 88;
        Semaphore semaphore = new Semaphore(2);

        IntStream.range(0, n).forEach(i -> {
            new Worker(i, semaphore).start();
        });
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 在子线程里控制资源占用
                semaphore.acquire();
                System.out.println("工人" + this.num + "占用一个机器在生产...");
                Thread.sleep(200);
                System.out.println("工人" + this.num + "释放出机器");
                // 在子线程里控制释放资源占用
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
