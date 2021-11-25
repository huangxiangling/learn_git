package cn.com.sheep.thread;

public class Counter {

    private int sum = 0;

    private Object oj = new Object();

    public void incR() {
        synchronized (oj) {
            sum = sum + 1;
        }
    }

    public int getSum() {
        return sum;
    }


    public static void main(String[] args) {
        int loop = 10000;
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incR();
        }
        System.out.println("single thread:" + counter.getSum());


        Counter counter1 = new Counter();
        new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter1.incR();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter1.incR();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread1 :" + counter1.getSum());
    }
}
