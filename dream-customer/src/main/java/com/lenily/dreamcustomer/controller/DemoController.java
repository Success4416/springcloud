package com.lenily.dreamcustomer.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lenily.dreamcustomer.service.DemoService;

@RestController
public class DemoController {

	@Autowired
	DemoService demoService;

	@RequestMapping(value = "getAccount",method = RequestMethod.GET)
	@ApiOperation(value = "测试接口",notes = "返回数字")
	public Integer getAccount() {
		System.out.println("customer");
		return 1;
//		return demoService.getAccount();
	}

}
