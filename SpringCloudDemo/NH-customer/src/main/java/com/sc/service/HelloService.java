package com.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @className HelloService.java
 * @author Nuri
 * @date Sep 2, 2019 1:34:12 PM   
*/
@Service
public class HelloService {
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="errorCallback")
	public String hello(String name) {
		System.out.println("Ribbon请求Service获取name:"+name);
		return restTemplate.getForObject("http://NH-PRODUCE/hello?name="+name, String.class);
	}
	
	/**
	 * Hystrix错误处理
	 * @return
	 */
	public String errorCallback(String name) {
		return "hello错误处理："+name+",Sorry,请求错误";
	}
}

