package com.itmuch.cloud.controller;

import com.itmuch.cloud.feign.UserFeignClient;
import com.itmuch.cloud.entity.User;
import com.itmuch.cloud.feign.UserFeignClient2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/7/8 0008.
 */
@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private UserFeignClient2 userFeignClient2;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id){

        return userFeignClient.findById(id);
    }

    @GetMapping("/{serviceName}")
    public String findById(@PathVariable String serviceName){

        return userFeignClient2.findEurekaByServiceName(serviceName);
    }

    /*@GetMapping("/test")
    public User testPost(User user){

        return userFeignClient.postUser(user);
    }

    // 该请求不会成功
    @GetMapping("/get-user")
    public User getUser(User user) {
        return userFeignClient.getUser(user);
    }*/

}
