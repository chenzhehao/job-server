package com.czh.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

//@Component
public class TestSchedule {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	CHCEmailService emailService;

	@Scheduled(cron = "0 */1 9-12 23 7 ?") // 每5秒执行一次
	public void scheduler0() {
		System.out.println(1231234234);
	}

	@Scheduled(cron = "0 0 13-23 21 7 ?") // 每5秒执行一次
	public void scheduler() {
		try {
			emailService.CHCSendEmail();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0 7-12 22 7 ?")  // 每5秒执行一次
	public void scheduler2() {
		try {
			emailService.CHCSendEmail();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0/10 12 22 7 ?")  // 每5秒执行一次
	public void scheduler3() {
		try {
			emailService.CHCSendEmail();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
