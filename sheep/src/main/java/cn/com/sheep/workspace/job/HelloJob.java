package cn.com.sheep.workspace.job;

import cn.com.sheep.week05.Hello;
import cn.com.sheep.workspace.annotation.QuartzAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
@DisallowConcurrentExecution
@QuartzAnnotation(clazz = HelloJob.class, cron = "*/5 * * * * ?")
public class HelloJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<String> list = new ArrayList<>();
        IntStream.range(0, 5).forEach(e -> {
            list.add(e + "");
        });

        System.out.println("====>" + Thread.currentThread().getName() + " hello 开始。。。。");
        list.parallelStream().forEach(e -> {

//            try {
//                if(e.equals("3")){
//                    int i = 1 / 0;
//                }
//                Thread.sleep(10000);
//                System.out.println("==========> : " + Thread.currentThread().getName() + " list = " + e);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
        });


        System.out.println("============> " + Thread.currentThread().getName() + "hello 结束。。。。");
    }
}
