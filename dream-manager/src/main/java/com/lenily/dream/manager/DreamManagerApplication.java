package com.lenily.dream.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lenily.dream.manager.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class DreamManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamManagerApplication.class, args);
	}

}
