package cn.com.sheep.workspace.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 多线程下提交的任务，设置超时返回
 * 超时：返回false
 * 未超时：返回true
 */
@Slf4j
public class TimeOutUtil {

    public static boolean checkTask(long timeout, Callable<Boolean> task) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Boolean> future = executorService.submit(task);
        boolean result = false;
        try {
            result = future.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("主线程在等待的时候被中断。。。");
        } catch (ExecutionException e) {
            log.error("子线程抛出异常。。。。");
        } catch (TimeoutException e) {
            log.error("子线程请求超时，中断任务。。。。");
            executorService.shutdownNow();
        } finally {
            executorService.shutdown();
        }
        return result;
    }

}
