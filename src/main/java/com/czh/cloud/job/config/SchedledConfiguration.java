package com.czh.cloud.job.config;

import com.czh.cloud.job.task.TestTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/8/1 14:53
 */
@Configuration
public class SchedledConfiguration {

    @Bean(name = "detailFactoryBean")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(TestTask testTask) {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(testTask);
        bean.setTargetMethod("scheduleTest");
        bean.setConcurrent(false);
        return bean;
    }

    @Bean(name = "cronTriggerBean")
    public CronTriggerFactoryBean cronTriggerBean(MethodInvokingJobDetailFactoryBean detailFactoryBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(detailFactoryBean.getObject());
        tigger.setCronExpression("0/3 * * * * ?");// 表示每隔6秒钟执行一次
        tigger.setName("myTigger");// trigger的name
        return tigger;
    }

    @Bean(name = "cronTriggerBean1")
    public CronTriggerFactoryBean cronTriggerBean1(MethodInvokingJobDetailFactoryBean detailFactoryBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(detailFactoryBean.getObject());
        tigger.setCronExpression("0/2 * * * * ?");// 表示每隔6秒钟执行一次
        tigger.setName("myTigger1");// trigger的name
        return tigger;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(Trigger[] cronTriggerBean) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //设置是否任意一个已定义的Job会覆盖现在的Job。默认为false，即已定义的Job不会覆盖现有的Job。
        //false，不允许覆盖，最早加入的起作用 true允许覆盖，最后加入的起作用(根据name)
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动5秒后 ，定时器才开始启动
        bean.setStartupDelay(5);
        // 注册定时触发器
        bean.setTriggers(cronTriggerBean);
        return bean;
    }
}
