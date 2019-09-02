package com.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @className ZuulApp.java
 * @author Nuri
 * @date Sep 2, 2019 8:18:23 PM   
*/
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApp {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApp.class, args);
	}
}

