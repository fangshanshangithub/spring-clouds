package com.service.feign.controller;

import com.service.feign.service.feign.FeignSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    private FeignSerive feignSerive;

    @GetMapping(value = "/sayHi")
    public String sayHi(@RequestParam String name) {
        return feignSerive.sayHiFromClientOne( name );
    }

    @GetMapping(value = "/sayHello")
    public String sayHello(@RequestParam String name) {
        return feignSerive.sayHiFromClientOne( name );
    }
}
