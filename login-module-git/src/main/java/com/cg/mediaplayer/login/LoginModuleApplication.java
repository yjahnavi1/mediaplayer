package com.cg.mediaplayer.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableFeignClients
@EnableSwagger2
public class LoginModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginModuleApplication.class, args);
	}

}
