package com.hello.demo.quartz.job;

import lombok.Setter;
import org.quartz.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 任务job
 */
@PersistJobDataAfterExecution
public class HelloJob implements Job {

    @Setter
    private Integer count;

    public HelloJob(){
        System.out.println("hello job");
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //do xxx
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(jobDataMap.getString("job detail 01"));

        JobDataMap jobDataMap1 = jobExecutionContext.getTrigger().getJobDataMap();
        System.out.println(jobDataMap1.getString("job trigger 01"));

        System.out.println(jobExecutionContext.getFireTime());

        System.out.println("count: " + count++);
        jobDataMap.put("count", count);
    }
}
