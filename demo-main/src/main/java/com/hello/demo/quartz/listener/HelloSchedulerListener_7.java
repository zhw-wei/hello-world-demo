package com.hello.demo.quartz.listener;

import org.quartz.*;

public class HelloSchedulerListener_7 implements SchedulerListener {

    //用于部署JobDetail时调用
    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("job scheduled " + trigger.getKey().getName());
    }

    //用于卸载JobDetail时调用
    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("job unscheduled " + triggerKey.getName());
    }

    //当一个trigger来到了在也不ui触发的状态时调用这个方法，除非这个job已设置成了持久性，否则它会从scheduler中移除
    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("trigger finalized " + trigger.getKey().getName());
    }

    //scheduler调用这个方法是发生在一个trigger或trigger组被暂停时，假如是trigger组的话，triggerName参数将为null
    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println("trigger paused " + triggerKey.getName());
    }

    //触发器组被暂停
    @Override
    public void triggersPaused(String triggerGroup) {
        System.out.println("hello world");

    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        System.out.println("hello world");

    }

    @Override
    public void triggersResumed(String triggerGroup) {
        System.out.println("hello world");

    }

    @Override
    public void jobAdded(JobDetail jobDetail) {

        System.out.println("hello world");
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        System.out.println("hello world");

    }

    @Override
    public void jobPaused(JobKey jobKey) {

        System.out.println("hello world");
    }

    @Override
    public void jobsPaused(String jobGroup) {

        System.out.println("hello world");
    }

    @Override
    public void jobResumed(JobKey jobKey) {

        System.out.println("hello world");
    }

    @Override
    public void jobsResumed(String jobGroup) {

        System.out.println("hello world");
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {

        System.out.println("hello world");
    }

    @Override
    public void schedulerInStandbyMode() {
        System.out.println("hello world");

    }

    @Override
    public void schedulerStarted() {
        System.out.println("hello world");

    }

    @Override
    public void schedulerStarting() {
        System.out.println("hello world");

    }

    @Override
    public void schedulerShutdown() {
        System.out.println("hello world");

    }

    @Override
    public void schedulerShuttingdown() {
        System.out.println("hello world");

    }

    @Override
    public void schedulingDataCleared() {
        System.out.println("hello world");

    }
}
