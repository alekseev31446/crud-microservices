package com.example.firstname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class FirstnameApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstnameApplication.class, args);
	}

}
