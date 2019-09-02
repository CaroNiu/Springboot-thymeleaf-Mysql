package com.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.service.HelloService;

/**
 * @className HelloController.java
 * @author Nuri
 * @date Sep 2, 2019 8:53:39 PM   
*/
@RestController
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/hello")
	private String hello(String name) {
		System.out.println("feign请求Controller进入");
		return helloService.hello(name);
	}
}

