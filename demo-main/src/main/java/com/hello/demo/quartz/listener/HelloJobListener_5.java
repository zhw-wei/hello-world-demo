package com.hello.demo.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class HelloJobListener_5 implements JobListener {
    @Override
    public String getName() {
        String name = this.getClass().getSimpleName();
        System.out.println("listener name: " + name);
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String name = context.getJobDetail().getKey().getName();
        System.out.println("to be executed, job name: " + name);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String name = context.getJobDetail().getKey().getName();
        System.out.println("vetoed, job name: " + name);

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String name = context.getJobDetail().getKey().getName();
        System.out.println("executed, job name: " + name);
    }
}
