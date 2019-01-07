package com.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @EnableEurekaClient 表明该项目是eureka的一个client端，用于产生service服务
 *
 * 对于 eureka client端 在eureka server端之前启动会报错，这是由于心跳的原因导致的，
 * 正常启动eureka server端之后就不会有什么影响
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

}

