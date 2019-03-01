package com.lenily.dreamcustomer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lenily.dreamcustomer.mapper")
public class DreamCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamCustomerApplication.class, args);
	}

	@Bean
	@LoadBalanced // 在注册中心里进行查找微服务,负载均衡
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
}
