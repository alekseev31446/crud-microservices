package com.example.middlename;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MiddlenameApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddlenameApplication.class, args);
	}

}
