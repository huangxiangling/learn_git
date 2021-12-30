package cn.com.sheep.workspace.job;

import cn.com.sheep.workspace.annotation.QuartzAnnotation;
import cn.com.sheep.workspace.utils.TimeOutUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Slf4j
@Component
@DisallowConcurrentExecution
@QuartzAnnotation(clazz = HelloJob.class, cron = "*/30 * * * * ?")
public class HelloJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 等待时间设置为11秒，任务将不会超时
        System.out.println("======== start ===========");
        boolean b = TimeOutUtil.checkTask(5, new ThreadJob());
        System.out.println("=======> 不会超时返回的结果： " + b);

        System.out.println("============> " + Thread.currentThread().getName() + "hello 结束。。。。");
    }

    class ThreadJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {


            List<String> list = new ArrayList<>();

            IntStream.range(0, 100).forEach(e -> {
                list.add(e + "");
            });
            AtomicBoolean flag = new AtomicBoolean(true);
            list.parallelStream().forEach(n -> {
                // 睡眠0.1秒
                try {
                    Thread.sleep(1000);
                    System.out.println("=======> " + Thread.currentThread().getName() + " :" + n);
                    if (Thread.interrupted()) {
                        flag.set(false);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            return flag.get();
        }
    }
}
