package com.itmuch.cloud.feign;

import com.itmuch.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Feign总结：
 *
 *      1.不支持@GetMappig，@PostMapping等注解
 *
 *      2.使用@PathVariable时 必须加上value，否则无法接受到参数
 *
 *      3.当movie服务发送get请求到user服务时，如果参数是自定义对象，那么
 *
 *          即使在FeignClient设置了RequestMethod.GET，依旧会以post请求
 *
 *          发送到user服务，导致405。这个时候需要将自定义对象拆开，用属性
 *
 *          分别接收（Long id,String userName...等等）
 *
 *      4.当user服务接受自定义对象时，movie调用user服务，需要使用@RequestBody，
 *
 *          否则无法接收到对象，用法见 postUser(@RequestBody User user)
 *
 */
@FeignClient(name = "microservice-provider-user", fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {
    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}




