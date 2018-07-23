package com.czh.springboot;

import com.czh.springboot.service.CHCEmailService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@EnableScheduling//增加支持定时任务的注解
@SpringBootApplication(scanBasePackages = "com.czh.springboot")
@MapperScan("com.czh.springboot.mapper")
@EnableDiscoveryClient
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    @Autowired
    CHCEmailService emailService;

    @RequestMapping("test")
    public String test() {
        try {
            emailService.CHCSendEmail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "asdf";
    }

}
