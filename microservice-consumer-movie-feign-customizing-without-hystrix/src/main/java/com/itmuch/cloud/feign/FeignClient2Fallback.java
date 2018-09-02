package com.itmuch.cloud.feign;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2018/8/8 0008.
 */
public class FeignClient2Fallback implements UserFeignClient2{
    @Override
    public String findEurekaByServiceName(@PathVariable("serviceName") String serviceName) {
        return "haha";
    }
}
