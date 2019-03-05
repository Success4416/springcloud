package com.lenily.dreamadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.lenily.dreamadmin.entity.Order;
import com.lenily.dreamadmin.mapper.OrderMapper;
import com.lenily.dreamadmin.service.CustomerService;
import com.lenily.dreamadmin.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	CustomerService customerService;

	@GlobalTransactional
	public Integer getMoney() {
		String xid = RootContext.getXID();
		RootContext.unbind();
		Order selectByPrimaryKey = orderMapper.selectByPrimaryKey(7);
		selectByPrimaryKey.setMoney(1000);
		orderMapper.updateByPrimaryKey(selectByPrimaryKey);
		customerService.getAccount();

		return selectByPrimaryKey.getMoney();
	}
}
