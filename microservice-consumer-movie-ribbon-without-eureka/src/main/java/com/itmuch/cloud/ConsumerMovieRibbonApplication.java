package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ConsumerMovieRibbonApplication {

	@Bean
	@LoadBalanced
	public RestTemplate rt(){
		return new RestTemplate();
	}

	public static void main(String[] args) {

		SpringApplication.run(ConsumerMovieRibbonApplication.class, args);
	}
}
