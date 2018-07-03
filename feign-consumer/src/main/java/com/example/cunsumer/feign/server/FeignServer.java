package com.example.cunsumer.feign.server;

import com.example.cunsumer.feign.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 默认会开启负载均衡
 * 如果第一个服务能直接返回，收到数据直接返回页面
 * 如果第一个服务断路器时间内没有返回（默认1秒），则会请求负载中的其他服务，以此类推
 */
@FeignClient("eureka-client")//绑定的服务
public interface FeignServer {

    @RequestMapping("/hello")//远程请求的接口
    public String hello();

    //RequestParam注解value参数不能少
    @RequestMapping(value = "hello1",method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name);

    @RequestMapping(value = "hello2",method = RequestMethod.GET)
    public User hello(@RequestParam("name") String name, @RequestParam("age") Integer age);

    /**
     * post 请求参数是对象，如果有重载的构造函数，必须也要有无参构造函数
     * @param user
     * @return
     */
    @RequestMapping(value = "hello3",method = RequestMethod.POST)
    public String hello(@RequestBody User user);


}
