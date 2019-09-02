package com.sc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className HelloService.java
 * @author Nuri
 * @date Sep 2, 2019 1:16:18 PM   
*/
@RestController
public class HelloService {
	@Value("${server.port}")
	private String port;
	
	/**
	 * 对外提供服务
	 * @param name
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello(String name) {
		System.out.println("生产者获取请求name:"+name);
		return "Hello:"+name+"From,Port:"+port;
	}
}

