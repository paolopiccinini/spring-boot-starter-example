package com.example.springbootconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.springbootstarterexample.domain")
public class SpringBootConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConsumerApplication.class, args);
	}

}
