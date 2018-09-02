package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/7/8 0008.
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id){

        return restTemplate.getForObject("http://microservice-provider-user/simple/" + id,User.class);
    }


    @GetMapping("/test")
    public String test(){

        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
        System.out.println(serviceInstance.getHost() + ":" + serviceInstance.getPort() + ":" + serviceInstance.getServiceId());

        ServiceInstance serviceInstance2 = loadBalancerClient.choose("microservice-provider-user3");
        System.out.println(serviceInstance2.getHost() + ":" + serviceInstance2.getPort() + ":" + serviceInstance2.getServiceId());

        return "i";
    }

    @GetMapping("/list-all")
    public List<User> listAll(){

        //错误用法，user服务返回的list，在movie模块通过restTemplate调用会返回List<LinkedHashMp>
        List<User> list = restTemplate.getForObject("http://microservice-provider-user/list-all",List.class);

        //正确写法
        User[] users = restTemplate.getForObject("http://microservice-provider-user/list-all",User[].class);
        List<User> list2 = Arrays.asList(users);

        return list2;

    }
}
