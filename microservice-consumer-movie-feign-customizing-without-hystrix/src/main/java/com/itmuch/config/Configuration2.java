package com.itmuch.config;

import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Administrator on 2018/7/27 0027.
 */
@Configuration
public class Configuration2 {

    /**
     * 认证配置
     *
     * application.yaml中配置了认证，但那个认证是为将服务注册到eureka上作的认证
     *
     *      UserFeignClient2是用来访问eureka服务的，需要单独配置认证
     *
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password123");
    }

    /**
     * 禁用使用该配置的FeignClient的Hystrix：
     *
     *      FeignClient默认配置类返回的是HystrixFeign.Builder
     *
     * @return
     */
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
