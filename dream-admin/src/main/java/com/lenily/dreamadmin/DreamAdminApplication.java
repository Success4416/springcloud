package com.lenily.dreamadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DreamAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamAdminApplication.class, args);
	}

}
