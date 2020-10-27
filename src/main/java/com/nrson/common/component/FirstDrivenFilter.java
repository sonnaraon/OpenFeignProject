package com.nrson.common.component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(FirstDrivenFilter.DEFAULT_ORDER)
public class FirstDrivenFilter implements Filter {
	
	public static final int DEFAULT_ORDER = Integer.MIN_VALUE;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		chain.doFilter(request, response);
	}
}
