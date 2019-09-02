package com.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @className RibbonApp.java
 * @author Nuri
 * @date Sep 2, 2019 1:30:32 PM   
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class RibbonApp {
	public static void main(String[] args) {
		SpringApplication.run(RibbonApp.class, args);
	}
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

