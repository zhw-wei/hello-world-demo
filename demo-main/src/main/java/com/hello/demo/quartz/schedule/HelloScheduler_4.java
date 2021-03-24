package com.hello.demo.quartz.schedule;

import com.hello.demo.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.util.Properties;

public class HelloScheduler_4 {

    public static void scheduler_4() throws SchedulerException, InterruptedException, IOException {
        Properties properties = new Properties();
        properties.load(HelloScheduler_2.class.getClassLoader().getResourceAsStream("quartz.properties"));
        Scheduler scheduler = new StdSchedulerFactory(properties).getScheduler();

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
