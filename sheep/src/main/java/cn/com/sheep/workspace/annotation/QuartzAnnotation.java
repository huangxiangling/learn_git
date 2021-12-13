package cn.com.sheep.workspace.annotation;


import org.quartz.Job;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface QuartzAnnotation {

    /**
     * job的class名称
     *
     * @return
     */
    Class<? extends Job> clazz();
    String jobDetail() default "";
    String trigger() default "";
    String cron() default "";
}
