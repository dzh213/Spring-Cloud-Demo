package com.example.cunsumer.feign.controller;

import com.example.cunsumer.feign.model.User;
import com.example.cunsumer.feign.server.FeignServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private FeignServer feignServer;


    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer() {
        return feignServer.hello();
    }

    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
    public String helloConsumer2() {
//        String result1 = feignServer.hello();
        String result2 = feignServer.hello("张三");
        User result3 = feignServer.hello("李四",12);
        String result4 = feignServer.hello(new User("王二",20));

//        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3.getName()+","+result3.getAge());
        System.out.println(result4);

        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(result1+"\n");
        stringBuilder.append(result2+"\n");
        stringBuilder.append(result3.toString()+"\n");
        stringBuilder.append(result4);
        return stringBuilder.toString();
    }
}
