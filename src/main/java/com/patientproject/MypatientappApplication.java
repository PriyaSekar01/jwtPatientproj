package com.patientproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableScheduling
public class MypatientappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MypatientappApplication.class, args);
	}

}
