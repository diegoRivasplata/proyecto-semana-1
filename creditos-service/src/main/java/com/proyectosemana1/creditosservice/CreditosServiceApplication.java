package com.proyectosemana1.creditosservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class CreditosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditosServiceApplication.class, args);
	}

}
