package com.sc.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @className HelloService.java
 * @author Nuri
 * @date Sep 2, 2019 8:29:18 PM   
*/
@FeignClient(value="NH-PRODUCE",fallback=HelloServiceImpl.class)
public interface HelloService {
	
	@RequestMapping("/hello")
	String hello(@RequestParam("name")String name);
}

