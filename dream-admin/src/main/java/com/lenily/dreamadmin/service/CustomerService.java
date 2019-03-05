package com.lenily.dreamadmin.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(value = "dream-customer")
public interface CustomerService {
	@RequestMapping(value = "/getAccount")
	public Integer getAccount();
}
