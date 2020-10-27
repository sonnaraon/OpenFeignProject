package com.nrson.test.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "feign", url = "http://localhost:8080")
public interface TestClient {

	@GetMapping("/testfeign")
	String testFeign();
}