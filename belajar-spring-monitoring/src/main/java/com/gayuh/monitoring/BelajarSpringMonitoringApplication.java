package com.gayuh.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BelajarSpringMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringMonitoringApplication.class, args);
	}

}
