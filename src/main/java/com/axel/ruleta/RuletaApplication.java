package com.axel.ruleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.axel.*")
@SpringBootApplication
public class RuletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuletaApplication.class, args);
	}

}
