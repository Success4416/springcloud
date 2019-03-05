package com.lenily.dreamcustomer;

import org.springframework.stereotype.Component;

import com.alibaba.fescar.core.context.RootContext;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignBasicRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		System.out.println("feign request XID:" + RootContext.getXID());
		template.header("fescar-XID", RootContext.getXID());
	}

}
