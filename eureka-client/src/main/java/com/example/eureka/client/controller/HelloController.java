package com.example.eureka.client.controller;

import com.example.eureka.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("hello")
    public String hello() {
        String services = "Services:"+discoveryClient.getServices();
        try {
            int sleepTime = new Random().nextInt(3000);
            System.out.println("停顿:"+sleepTime);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(services);
        return services;
    }

    //这里的注解@RequestParam的value可以省略，Spring MVC会默认参数的名做Value值
    @RequestMapping(value = "hello1",method = RequestMethod.GET)
    public String hello1(@RequestParam String name) {
        return "Hello" + name;
    }

    @RequestMapping(value = "hello2",method = RequestMethod.GET)
    public User hello2(@RequestParam String name, @RequestHeader Integer age) {
        return new User(name,age);
    }

    @RequestMapping(value = "hello3",method = RequestMethod.POST)
    public String hello3(@RequestBody User user) {
        return "Hello"+","+user.getName()+",,"+user.getAge();
    }

}
