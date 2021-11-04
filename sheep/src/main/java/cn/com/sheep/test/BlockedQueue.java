package cn.com.sheep.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {

    private final Lock lock = new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty = lock.newCondition();


    // 入队
    private void enq(T x){
        lock.lock();
        try {
            // 队列已满
            while (true){
                // 等待队列不满
                notFull.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    // 出队
    private void deq(){

        lock.lock();
        try {
            while(true){
                notEmpty.await();
            }
            // 出队后，通知可入队
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
