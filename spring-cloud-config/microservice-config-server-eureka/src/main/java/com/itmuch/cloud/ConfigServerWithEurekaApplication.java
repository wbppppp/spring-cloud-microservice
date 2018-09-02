package com.itmuch.cloud;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerWithEurekaApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ConfigServerWithEurekaApplication.class,args);
    }
}
