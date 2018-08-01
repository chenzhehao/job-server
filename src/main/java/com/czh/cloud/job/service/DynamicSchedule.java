package com.czh.cloud.job.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description: 可动态修改定时任务规则
 * @date: 2018/7/23 9:31
 */
//@Component
//@EnableScheduling
public class DynamicSchedule implements SchedulingConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(DynamicSchedule.class);

    private static String cron;

    public DynamicSchedule() {
        cron = "0/5 * * * * ?";

        // 开启新线程模拟外部更改了任务执行周期
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable to: " + cron);
                try {
                    Thread.sleep(15 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cron = "0/10 * * * * ?";
                System.out.println("cron change to: " + cron);
            }
        }).start();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                // 任务逻辑
                System.out.println("dynamicCronTask is running...");
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 任务触发，可修改任务的执行周期
                // 可再次修改任务执行周期，查询数据库，或者查询缓存等等即可
                CronTrigger trigger = new CronTrigger(cron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        });
    }
}
