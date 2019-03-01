package com.lenily.dreamadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	DemoService demoService;

	@RequestMapping("getAccount")
	public Integer getAccount() {

		// ResponseEntity<Integer> forEntity =
		// restTemplate.getForEntity("http://dream-customer/getAccount", Integer.class);

		return demoService.getMoney();

	}

}
