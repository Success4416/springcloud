package com.lenily.dream.manager.controller;

import com.lenily.dream.manager.anno.ExceptionListener;
import com.lenily.dream.manager.service.DemoService;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class DemoController {

	Logger logger = LoggerFactory.getLogger(DemoController.class);

//	@Autowired
//	RestTemplate restTemplate;
	@Autowired
	DemoService demoService;

	@ExceptionListener
	@RequestMapping("getAccount")
	public Integer getAccount(){

		// ResponseEntity<Integer> forEntity =
		// restTemplate.getForEntity("http://dream-customer/getAccount", Integer.class);

		// return demoService.getMoney();
//		demoService.getMoney();
		logger.error("娃哈哈！");
			Succc succc = new Succc();
			System.out.println(succc.getName().equals("sss"));
		return null;
	}
	class Succc{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<>();
		strings.add("123");
		strings.add("2342");
		List<String> dst = Arrays.asList(new String[strings.size()]);
		Collections.copy(dst,strings);
		dst.stream().forEach(System.out::println);

		System.out.println("第一个集合");
		strings.add("13413123");
		strings.stream().forEach(System.out::println);

	}

}
