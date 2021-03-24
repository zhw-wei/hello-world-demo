package com.hello.demo.quartz.schedule;

import com.hello.demo.quartz.job.HelloJob;
import com.hello.demo.quartz.listener.HelloSchedulerListener_7;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.util.Properties;

public class HelloScheduler_7 {

    public static void scheduler_7() throws SchedulerException, InterruptedException, IOException {
        Properties properties = new Properties();
        properties.load(HelloScheduler_2.class.getClassLoader().getResourceAsStream("quartz.properties"));
        Scheduler scheduler = new StdSchedulerFactory(properties).getScheduler();

        String jobKey = "hello job detail 07";
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job detail 02", "job detail 02 value")
                .usingJobData(jobKey, jobKey)
                .build();

        String triggerKey = "cron trigger 07";
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("cron trigger 02", "cron trigger 02 value")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .usingJobData(triggerKey, true)
                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.getListenerManager().addSchedulerListener(new HelloSchedulerListener_7());

        //启动任务
        scheduler.start();
    }
}
