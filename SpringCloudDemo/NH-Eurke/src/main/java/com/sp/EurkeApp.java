package com.sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eurke服务启动类
 * @className EurkeApp.java
 * @author Nuri
 * @date Sep 2, 2019 1:02:18 PM   
*/
@SpringBootApplication
@EnableEurekaServer
public class EurkeApp {
	public static void main(String[] args) {
		SpringApplication.run(EurkeApp.class, args);
	}
}

