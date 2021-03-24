package com.hello.demo.quartz.schedule;

import com.hello.demo.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;

public class HelloScheduler_3 {

    public static void scheduler_3() throws SchedulerException, InterruptedException {
        // TODO: 2019/9/11 未调通，待解决
        Scheduler scheduler = DirectSchedulerFactory.getInstance().getScheduler();

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
