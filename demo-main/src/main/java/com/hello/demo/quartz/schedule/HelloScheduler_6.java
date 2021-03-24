package com.hello.demo.quartz.schedule;

import com.hello.demo.quartz.job.HelloJob;
import com.hello.demo.quartz.listener.HelloJobListener_5;
import com.hello.demo.quartz.listener.HelloTriggerListener_6;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

import java.io.IOException;
import java.util.Properties;

public class HelloScheduler_6 {

    public static void scheduler_6() throws SchedulerException, InterruptedException, IOException {
        Properties properties = new Properties();
        properties.load(HelloScheduler_2.class.getClassLoader().getResourceAsStream("quartz.properties"));
        Scheduler scheduler = new StdSchedulerFactory(properties).getScheduler();

        String jobKey = "hello job detail 06";
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job detail 02", "job detail 02 value")
                .usingJobData(jobKey, jobKey)
                .build();

        String triggerKey = "cron trigger 06";
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("cron trigger 02", "cron trigger 02 value")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .usingJobData(triggerKey, true)
                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);

        //创建并注册一个全局的TriggerListener
        scheduler.getListenerManager().addTriggerListener(new HelloTriggerListener_6(), EverythingMatcher.allTriggers());

        //创建并注册一个局部的TriggerListener
        scheduler.getListenerManager().addTriggerListener(new HelloTriggerListener_6(),
                KeyMatcher.keyEquals(TriggerKey.triggerKey(triggerKey, triggerKey)));

        //启动任务
        scheduler.start();
    }
}
