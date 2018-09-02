package com.itmuch.cloud.feign;

import com.itmuch.config.Configuration2;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/7/27 0027.
 */
@FeignClient(name = "xxx", url = "http://localhost:8761/", configuration = Configuration2.class,fallback = FeignClient2Fallback.class)
public interface UserFeignClient2 {

    @RequestMapping(value = "/eureka/apps/{serviceName}",method = RequestMethod.GET)
    public String findEurekaByServiceName(@PathVariable("serviceName") String serviceName);
}
