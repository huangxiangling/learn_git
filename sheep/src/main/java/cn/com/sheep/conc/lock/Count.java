package cn.com.sheep.conc.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Count {

    private final ReentrantLock lock = new ReentrantLock();

    public void get(){
        try {
            lock.lock();
            System.out.println("get begin");
            Thread.sleep(1000);
            System.out.println("get end");
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
