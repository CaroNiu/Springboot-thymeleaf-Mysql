package com.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @className FeignApp.java
 * @author Nuri
 * @date Sep 2, 2019 8:28:03 PM   
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignApp {
	public static void main(String[] args) {
		SpringApplication.run(FeignApp.class, args);
	}
}

