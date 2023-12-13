package com.javawebmvc.JavaWebMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class JavaWebMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaWebMvcApplication.class, args);
	}

}
