package com.example.statistique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrix
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
public class PlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerApplication.class, args);
	}

}
