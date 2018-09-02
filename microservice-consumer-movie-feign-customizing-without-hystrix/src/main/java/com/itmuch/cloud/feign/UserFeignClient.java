package com.itmuch.cloud.feign;

import com.itmuch.config.Configuration1;
import com.itmuch.cloud.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

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
@FeignClient(name = "microservice-provider-user",configuration = Configuration1.class,fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @RequestLine("GET /simple/{id}")
    User findById(@Param("id") Long id);

    /*@RequestMapping(value = "/user", method = RequestMethod.POST)
    public User postUser(@RequestBody User user);

    //该请求失败，报错405
    @RequestMapping(value = "/get-user", method = RequestMethod.GET)
    public User getUser(User user);*/
}
