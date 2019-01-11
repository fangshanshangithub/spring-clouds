package com.service.feign.hystric;

import com.service.feign.service.feign.FeignSerive;
import org.springframework.stereotype.Component;

/**
 * HiHystric需要实现FeignSerive 接口，并注入到Ioc容器中
 */
@Component
public class HiHystric implements FeignSerive {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sayHiFromClientOne: Sorry --- " + name;
    }

    @Override
    public String sayHelloFromClientOne(String name) {
        return "sayHelloFromClientOne: Sorry --- " + name;
    }
}
