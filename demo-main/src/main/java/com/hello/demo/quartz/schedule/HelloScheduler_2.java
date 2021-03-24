package com.hello.demo.quartz.schedule;

import com.hello.demo.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class HelloScheduler_2 {

    public static void scheduler_2() throws SchedulerException, InterruptedException, IOException {

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

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

        //等待2秒
        Thread.sleep(2000);

        //挂起
        scheduler.standby();
        //再次启动
        scheduler.start();

        scheduler.shutdown(true);
    }
}
