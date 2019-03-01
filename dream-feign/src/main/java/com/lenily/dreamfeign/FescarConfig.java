package com.lenily.dreamfeign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class FescarConfig {

	@Bean
	public RequestInterceptor requestInterceptor() {
		FeignBasicRequestInterceptor requestInterceptor = new FeignBasicRequestInterceptor();
		return requestInterceptor;
	}

	@Bean
	public FescarXidFilter fescarXidFilter() {
		return new FescarXidFilter();
	}

}
