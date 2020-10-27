package com.nrson.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class ContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ContextUtil.ctx = applicationContext;
	}
	
	public static Object getBean(final String beanName) throws BeansException, IllegalStateException {
		return Optional.ofNullable(ctx)
				.orElseThrow(() -> new IllegalStateException(
						"CAN'T call a getBean method before an application context should be initializated."))
				.getBean(beanName);
	}
	
	public static <T> T getBean(String beanName, Class<T> requiredType) throws BeansException, IllegalStateException {
		return Optional.ofNullable(ctx)
				.orElseThrow(() -> new IllegalStateException(
						"CAN'T call a getBean method before an application context should be initializated."))
				.getBean(beanName, requiredType);
	}
	
	
	@SuppressWarnings("deprecation")
	public static JsonElement loadClassPathJsonAndGetBean(final String jsonFilePath) {
		final JsonParser parser = new JsonParser();
		
		final JsonElement jsonElm;
		try(InputStream instm = new ClassPathResource(jsonFilePath).getInputStream()) {
			jsonElm = parser.parse(new InputStreamReader(instm, StandardCharsets.UTF_8));
		} catch(final IOException e) {
			throw new RuntimeException(
					"can't build a specified instance because of unavailable to read a file", e);
		}
		
		return jsonElm;
	}
	
	public static <T> T loadClassPathJsonAndGetBean(final String jsonFilePath, Class<T> targetClass) {
		final JsonElement jsonElem = loadClassPathJsonAndGetBean(jsonFilePath);
		
		final Gson gson = new Gson();
		return gson.fromJson(jsonElem, targetClass);
	}

}
