package com.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sc.service.HelloService;

/**
 * @className HelloController.java
 * @author Nuri
 * @date Sep 2, 2019 1:33:00 PM   
*/
@RestController
public class HelloController {
	@Autowired
	private HelloService helloService;

	@RequestMapping("/hello")
	public String hello(@RequestParam("name") String name) {
		System.out.println("Ribbon请求获取name:"+name);
		return helloService.hello(name);
	}
}

