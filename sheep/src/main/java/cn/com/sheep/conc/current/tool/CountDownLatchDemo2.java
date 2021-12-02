package cn.com.sheep.conc.current.tool;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Slf4j
public class CountDownLatchDemo2 {

    private final static int threadCount = 200;
    //线程个数
    private static int THREAD_COUNT = 10;
    // 总元素数量
    private static int ITEM_COUNT = 1000;

    private static Object oj = new Object();
    private static ReentrantLock lock = new ReentrantLock();
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
//
//        IntStream.range(0, threadCount).forEach(i -> {
//            final int threadNum = i;
//            executorService.execute(() ->{
//                try {
//                    test(threadNum);
//                    //countDownLatch.countDown();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    countDownLatch.countDown();
//                }
//            });
//        });


        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        log.info("init size:{}", concurrentHashMap.size());


        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            //下面的这段复合逻辑需要锁一下这个ConcurrentHashMap
            synchronized (oj) {
                int gap = ITEM_COUNT - concurrentHashMap.size();
                log.info("gap size:{}", gap);
                concurrentHashMap.putAll(getData(gap));
            }

        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);


        log.info("finish size:{}", concurrentHashMap.size());


    }


    // 帮助方法，用来获得一个指定元素数量模拟数据的ConcurrentHashMap
    private static ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count).boxed().collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
    }


    private static void test(int threadNum) throws Exception {
        Thread.sleep(100);
        System.out.println(String.format("程序员[%d]完成任务。。。", threadNum));
        Thread.sleep(100);
    }
}
