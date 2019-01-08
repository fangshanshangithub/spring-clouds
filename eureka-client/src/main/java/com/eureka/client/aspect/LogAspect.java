package com.eureka.client.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect //将一个类定义为一个切面类
@Order(1) //标记切面类的处理优先级,i值越小,优先级别越高.PS:可以注解类,也能注解到方法上
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Gson gson = new Gson();

    /**
     * @Pointcut注解中的execution表达式: public * com.demo.*.*(..)
     *
     * 第一个 public 表示方法的修饰符,可以用*代替
     * 第一个 * 表示 返回值,*代表所有
     * com.demo.* 包路径,.*表示路径下的所有包
     * 第三个.* 表示路径下,所有包下的所有类的方法
     * (..) 表示不限方法参数
     */
    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com..controller..*.*(..))")
    private void controllerAspect(){

    }


    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址:"+request.getRequestURL().toString());
        log.info("请求方式:"+request.getMethod());
        log.info("请求类方法:"+joinPoint.getSignature());
        log.info("请求类方法参数:"+ Arrays.toString(joinPoint.getArgs()));
        log.info("===============请求内容===============");
    }


    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o",pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o ){
        log.info("--------------返回内容----------------");
        log.info("Response内容:"+gson.toJson(o));
        log.info("--------------返回内容----------------");
    }

}
