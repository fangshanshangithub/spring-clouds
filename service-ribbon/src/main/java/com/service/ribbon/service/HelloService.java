package com.service.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;


    /**
     *@HystrixCommand(fallbackMethod = "异常处理方法")
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://EUREKA-HELLO/hi?name="+name,String.class);
    }

    /**
     * 断路器 触发了 异常，调用该方法
     * @param name
     * @return
     */
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
