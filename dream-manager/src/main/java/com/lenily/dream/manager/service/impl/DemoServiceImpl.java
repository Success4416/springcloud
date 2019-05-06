package com.lenily.dream.manager.service.impl;

import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.lenily.dream.manager.entity.Order;
import com.lenily.dream.manager.mapper.OrderMapper;
import com.lenily.dream.manager.service.CustomerService;
import com.lenily.dream.manager.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	private OrderMapper orderMapper;
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
