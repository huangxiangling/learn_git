package cn.com.sheep.conc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Count2 {

    /**
     * 读写锁
     */
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void get() {
        rwLock.readLock().lock();
        try {
            System.out.println("get begin");

            Thread.sleep(1000);

            System.out.println("get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void put() {
        rwLock.writeLock().lock();
        try {
            System.out.println("put begin");
            Thread.sleep(1000);
            System.out.println("put end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
