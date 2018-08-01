package com.czh.cloud.job;

import com.czh.cloud.job.service.CHCEmailService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling//增加支持定时任务的注解
@SpringBootApplication(scanBasePackages = "com.czh.cloud")
@MapperScan("com.czh.cloud.job.mapper")
@EnableDiscoveryClient
public class BootOfJobServer {
    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            System.setProperty("log.path", "D:/chenzhehao/workspace/czh");
        } else {
            System.setProperty("log.path", "/opt");
        }
        System.setProperty("context.name", "job-server");

        SpringApplication.run(BootOfJobServer.class, args);
    }

    @Autowired
    CHCEmailService emailService;

    @RequestMapping("test")
    public String test() {
//        try {
//            emailService.CHCSendEmail();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        return "job test success";
    }

}
