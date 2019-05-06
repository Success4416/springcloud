package com.lenily.dreamcustomer.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fescar.core.context.RootContext;

@Component

public class FescarXidFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String xid = RootContext.getXID();
		String restXid = request.getHeader("fescar-XID");
		boolean bind = false;
		if (StringUtils.isBlank(xid) && StringUtils.isNotBlank(restXid)) {
			RootContext.bind(restXid);
			bind = true;
		}
		try {
			filterChain.doFilter(request, response);
		} finally {
			if (bind) {
				RootContext.unbind();
			}
		}
	}

}
