package cn.com.sheep.conc.current.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future result = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        executorService.shutdown();

        try {
            System.out.println("result:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
