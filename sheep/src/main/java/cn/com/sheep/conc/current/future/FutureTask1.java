package cn.com.sheep.conc.current.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTask1 {

    public static void main(String[] args) throws Exception {

        // 第一种方式
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        new Thread(task).start();

        // 第二种方式
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<Integer> task1 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        executorService.submit(task1);

        try {
            System.out.println("result: " + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
