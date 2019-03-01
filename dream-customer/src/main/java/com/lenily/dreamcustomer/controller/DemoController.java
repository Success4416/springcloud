package com.lenily.dreamcustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lenily.dreamcustomer.service.DemoService;

@RestController
public class DemoController {

	@Autowired
	DemoService demoService;

	@RequestMapping("getAccount")
	public Integer getAccount() {

		return demoService.getAccount();
	}
}
