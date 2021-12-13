package cn.com.sheep.workspace.config;

import cn.com.sheep.workspace.annotation.QuartzAnnotation;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
public class QuartzConfig {

    @Autowired
    private GenericWebApplicationContext applicationContext;


    /**
     * 注册jobDetail和trigger实例
     *
     * @return
     */
    @Bean
    public Object registerCustomBean() {

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(QuartzAnnotation.class);
        beans.forEach((k, v) -> {
            Class aClass = v.getClass();
            QuartzAnnotation annotation = (QuartzAnnotation) aClass.getAnnotation(QuartzAnnotation.class);
            String jobDetail = annotation.jobDetail().isEmpty() ? k : annotation.jobDetail();
            String trigger = annotation.trigger().isEmpty() ? k + "Trigger" : annotation.trigger();
            applicationContext.registerBean(k, JobDetail.class, () -> JobBuilder
                    .newJob(aClass)
                    .withIdentity(jobDetail)
                    .storeDurably().build());

            CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(annotation.cron());
            applicationContext.registerBean(trigger, Trigger.class, () -> TriggerBuilder
                    .newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(trigger)
                    .withSchedule(schedule).build());

        });
        return new Object();
    }

    @Bean
    public Executor schedulerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(25);
        executor.setQueueCapacity(100);
        return executor;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource, JobDetail[] jobDetails, Trigger[] triggers) throws Exception {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(createJobFactory());
        factoryBean.setTaskExecutor(schedulerThreadPool());
        factoryBean.setQuartzProperties(quartzProperties());
        factoryBean.setDataSource(dataSource);
        factoryBean.afterPropertiesSet();
        factoryBean.setAutoStartup(true);
        factoryBean.setJobDetails(jobDetails);
        factoryBean.setTriggers(triggers);
        return factoryBean;
    }

    @Bean
    public MyJobFactory createJobFactory() {
        return new MyJobFactory();
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
