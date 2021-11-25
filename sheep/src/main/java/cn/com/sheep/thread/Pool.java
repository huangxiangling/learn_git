package cn.com.sheep.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

    }
}
