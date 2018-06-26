package com.example.consumer.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer")
    @HystrixCommand(fallbackMethod = "errorFallback")
    public String consumer() {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://EUREKA-CLIENT/hello",String.class).getBody();
        long end = System.currentTimeMillis();
        System.out.println("消费服务获取返回结果："+result+",耗时："+(end-start));
        return result;
    }

    //测试1秒以上响应就会执行断路器fallback，与书上说的默认2000毫秒不符
    public String errorFallback() {
        return "error";
    }
}
