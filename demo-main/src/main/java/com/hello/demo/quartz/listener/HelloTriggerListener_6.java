package com.hello.demo.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class HelloTriggerListener_6 implements TriggerListener {
    @Override
    public String getName() {
        String name = this.getClass().getSimpleName();
        System.out.println("name: " + name);
        return name;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        String name = trigger.getKey().getName();
        System.out.println("trigger fired: " + name);
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        String name = trigger.getKey().getName();
        System.out.println("job execution: " + name);
        return false;//true表示不执行job的方法
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        String name = trigger.getKey().getName();
        System.out.println("trigger misfired: " + name);
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        String name = trigger.getKey().getName();
        System.out.println("trigger complete: " + name);
    }
}
