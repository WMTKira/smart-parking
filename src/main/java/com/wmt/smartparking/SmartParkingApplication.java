package com.wmt.smartparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wmtumanday
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SmartParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartParkingApplication.class, args);
	}

}
