package com.nrson.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nrson.common.component.SPrePostHanderInterceptor;

@Configuration
public class HttpInterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private SPrePostHanderInterceptor httpInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(httpInterceptor)
		        .addPathPatterns("/**")
		        .excludePathPatterns("/static/**");
	}

}
