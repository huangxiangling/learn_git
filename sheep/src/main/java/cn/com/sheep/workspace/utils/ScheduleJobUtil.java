package cn.com.sheep.workspace.utils;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;

public class ScheduleJobUtil {

    /**
     * 创建定时任务
     */
//    public static void createScheduleJob(Scheduler scheduler, SysJob job) {
//        try {
//            //构建job信息
//            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(job.getJobId())).build();
//            //表达式调度构建器
//            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
//            cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);
//
//            //按新的cronExpression表达式构建一个新的trigger
//            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(job.getJobId()))
//                    .withSchedule(cronScheduleBuilder).build();
//
//            //放入参数，运行时的方法可以获取
//            jobDetail.getJobDataMap().put(ScheduleJobConstant.TASK_PROPERTIES, job);
//            //执行器添加 定时任务(自动存入数据库)
//            scheduler.scheduleJob(jobDetail, trigger);
//            //暂停任务
//            if (job.getStatus().equals(ScheduleJobConstant.Status.PAUSE.getValue())) {
//                pauseJob(scheduler, job.getJobId());
//            }
//        } catch (Exception e) {
//            log.error("createScheduleJob 异常：", e);
//        }
//    }
//
//    /**
//     * 表达式与触发器构建
//     * @param job
//     * @param cb
//     * @return
//     * @throws Exception
//     */
//    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob job, CronScheduleBuilder cb)
//            throws Exception {
//        switch (job.getMisfirePolicy()) {
//            case ScheduleJobConstant.MISFIRE_DEFAULT:
//                return cb;
//            case ScheduleJobConstant.MISFIRE_IGNORE_MISFIRES:
//                //不触发立即执行
//                //——等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
//                return cb.withMisfireHandlingInstructionIgnoreMisfires();
//            case ScheduleJobConstant.MISFIRE_FIRE_AND_PROCEED:
//                //以错过的第一个频率时间立刻开始执行
//                //——重做错过的所有频率周期后
//                //——当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行
//                return cb.withMisfireHandlingInstructionFireAndProceed();
//            case ScheduleJobConstant.MISFIRE_DO_NOTHING:
//                // 不触发立即执行
//                // 等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
//                return cb.withMisfireHandlingInstructionDoNothing();
//            default:
//                throw new Exception("The task misfire policy '" + job.getMisfirePolicy() + "' cannot be used in cron schedule tasks");
//        }
//    }　
}
