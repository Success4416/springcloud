package com.lenily.dreamcustomer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.lenily.dreamcustomer.entity.Account;
import com.lenily.dreamcustomer.mapper.AccountMapper;
import com.lenily.dreamcustomer.mapper.OrderMapper;
import com.lenily.dreamcustomer.mapper.StorageMapper;
import com.lenily.dreamcustomer.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private StorageMapper storageMapper;

	@Override
	public Integer getAccount() {
		String xid = RootContext.getXID();

		Account selectByPrimaryKey = accountMapper.selectByPrimaryKey(1);
		selectByPrimaryKey.setMoney(9090909);
		accountMapper.updateByPrimaryKey(selectByPrimaryKey);

		if (1 == 1) {
			throw new RuntimeException();
		}

		return selectByPrimaryKey.getMoney();
	}
}
