package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableHystrixDashboard //启用Hystrix Dashboard
public class HystrixDashboardApplication {
    public static void main( String[] args ) {
        SpringApplication.run(HystrixDashboardApplication.class,args);
    }
}