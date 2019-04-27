package com.eureka.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @EnableEurekaClient 表明该项目是eureka的一个client端，用于产生service服务
 *
 * 对于 eureka client端 在eureka server端之前启动会报错，这是由于心跳的原因导致的，
 * 正常启动eureka server端之后就不会有什么影响
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {
	private static final Logger log = LoggerFactory.getLogger(EurekaClientApplication.class);

	private final Environment env;

	public EurekaClientApplication(Environment env) {
		this.env = env;
	}



	public static void main(String[] args) {
		//SpringApplication.run(EurekaClientApplication.class, args); // 启动不显示url访问参数

		SpringApplication app = new SpringApplication(EurekaClientApplication.class);


		Environment env = app.run(args).getEnvironment();
		startShowInfos(env);

	}

	/**
	 * 该方法主要是springboot 启动之后答应相关信息，通过Environment类
	 * @param env
	 */
	private static void startShowInfos(Environment env) {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		try {
			log.info("\n《《《《《《《《======================================================》》》》》》》》》》\n\t" +
							"Application '{}' is running! Access URLs:\n\t" +
							"Local: \t\t{}://localhost:{}\n\t" +
							"External: \t{}://{}:{}\n\t" +
							"Profile(s): \t{}\n《《《《《《《《======================================================》》》》》》》》》》",
					env.getProperty("spring.application.name"),
					protocol,
					env.getProperty("server.port"),
					protocol,
					InetAddress.getLocalHost().getHostAddress(),
					env.getProperty("server.port"),
					env.getActiveProfiles());
		} catch (UnknownHostException e) {
			log.error("项目启动出现异常{}", e.getMessage());
			e.printStackTrace();
		}
	}

}

