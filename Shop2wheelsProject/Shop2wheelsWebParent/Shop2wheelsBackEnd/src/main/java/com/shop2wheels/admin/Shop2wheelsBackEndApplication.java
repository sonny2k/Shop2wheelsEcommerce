package com.shop2wheels.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({ "com.shop2wheels.common.entity", "com.shop2wheels.admin.user" })
public class Shop2wheelsBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(Shop2wheelsBackEndApplication.class, args);
	}

}
