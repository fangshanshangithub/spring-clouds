package com.service.feign.service.feign;

import com.service.feign.hystric.HiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign 是一个伪的http请求调用注册中心中的服务
 */
@FeignClient(value = "eureka-hello", fallback = HiHystric.class) //定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务
public interface FeignSerive {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String sayHelloFromClientOne(@RequestParam(value = "name") String name);
}
