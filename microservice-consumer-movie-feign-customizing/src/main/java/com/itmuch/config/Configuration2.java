package com.itmuch.config;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
