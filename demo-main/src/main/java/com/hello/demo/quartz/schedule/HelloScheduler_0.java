package com.hello.demo.quartz.schedule;

import com.hello.demo.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler_0 {
    public static void scheduler_0() throws SchedulerException {
        //1. 调度器 scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2. 任务实例 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job01", "group01")
                .usingJobData("job detail 01", "job detail 01 value")
                .usingJobData("count", 0)
                .build();
        System.out.println(jobDetail.getKey().getName());
        System.out.println(jobDetail.getKey().getGroup());
        System.out.println(jobDetail.getJobClass().getName());

        //3. 触发器 Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger01", "group01")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(5).withIntervalInSeconds(5))
                .usingJobData("job trigger 01", "job trigger 01 value")
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
