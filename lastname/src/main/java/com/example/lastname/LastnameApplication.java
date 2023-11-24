package com.example.lastname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LastnameApplication {

	public static void main(String[] args) {
		SpringApplication.run(LastnameApplication.class, args);
	}

}
