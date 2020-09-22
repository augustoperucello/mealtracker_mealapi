package com.augusto.mealservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.augusto.oauth2learning.Controllers")
@ComponentScan("com.augusto.oauth2learning.Service")
public class LoginappApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginappApplication.class, args);
	}

}
