package com.hello.demo.quartz.schedule;

import com.hello.demo.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler_1 {

    public static void scheduler_1() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job detail 02", "job detail 02 value")
                .build();

        //定时任务
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("cron trigger 02", "cron trigger 02 value")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }
}
