package com.czh.cloud.job.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/8/1 15:01
 */
@Component("testTask")
public class TestTask implements Job {
    Logger logger = LoggerFactory.getLogger(TestTask.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("123123");
        System.err.println("123123");
    }

    public void scheduleTest() {
        System.err.println("scheduleTest开始定时执行");
    }
}
