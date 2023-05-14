package com.cg.mediaplayer.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginServiceApplication.class, args);
	}

}
