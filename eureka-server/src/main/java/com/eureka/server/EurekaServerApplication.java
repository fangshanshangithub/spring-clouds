package com.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * springcloud eureka 服务端 启动注解
 * eureka server端其实是一个特殊的eureka clent端
 *
 * 本项目是eureka 的一个服务端，主要是接受clent端的服务注册，相当是一个服务注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}

