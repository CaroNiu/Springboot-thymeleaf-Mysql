package com.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @className ProdeceApp.java
 * @author Nuri
 * @date Sep 2, 2019 1:19:23 PM   
*/
@SpringBootApplication
@EnableEurekaClient
public class ProdeceApp {
	public static void main(String[] args) {
		SpringApplication.run(ProdeceApp.class, args);
	}
}

