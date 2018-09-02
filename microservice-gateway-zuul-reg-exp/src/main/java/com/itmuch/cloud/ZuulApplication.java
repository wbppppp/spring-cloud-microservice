package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
    public static void main( String[] args ) {

        SpringApplication.run(ZuulApplication.class,args);
    }

    /**
     * 将微服务microservice-provider-user-v1路由地址为
     *      http://localhost:8040/v1/micorservice-provider-user/simple/1
     *
     *   若服务名称为microservice-provider-user,则路由地址为
     *      http://localhost:8040/micorservice-provider-user/simple/1
     *
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }
}
