package com.sc.service;
/**
 * @className HelloServiceImpl.java
 * @author Nuri
 * @date Sep 2, 2019 8:30:56 PM   
*/
public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String name) {
		return "feign请求，hello:"+name+",请求错误";
	}

}

