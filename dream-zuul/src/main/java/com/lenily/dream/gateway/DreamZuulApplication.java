package com.lenily.dream.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy//启用 zuul,自带熔断
public class DreamZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamZuulApplication.class, args);
	}

}
