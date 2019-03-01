package com.lenily.dreamfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;

@RestController
public class DemoController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("getMoney")
	@GlobalTransactional
	public String all() {
		String xid = RootContext.getXID();

		ResponseEntity<Integer> ss = restTemplate.getForEntity("http://dream-admin/getAccount", Integer.class);
		Integer aa = ss.getBody();
		ResponseEntity<Integer> forEntity = restTemplate.getForEntity("http://dream-customer/getAccount",
				Integer.class);
		Integer body = forEntity.getBody();

		return "Account" + body + "money" + aa;
	}
}
