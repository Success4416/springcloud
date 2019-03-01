package com.lenily.dreamadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.lenily.dreamadmin.entity.Order;
import com.lenily.dreamadmin.mapper.OrderMapper;

@Service
public class DemoService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	RestTemplate restTemplate;

	@GlobalTransactional
	public Integer getMoney() {
		String xid = RootContext.getXID();
		Order selectByPrimaryKey = orderMapper.selectByPrimaryKey(7);
		selectByPrimaryKey.setMoney(1000);
		orderMapper.updateByPrimaryKey(selectByPrimaryKey);

		ResponseEntity<Integer> forEntity = restTemplate.getForEntity("http://dream-customer/getAccount",
				Integer.class);
		return selectByPrimaryKey.getMoney();
	}
}
