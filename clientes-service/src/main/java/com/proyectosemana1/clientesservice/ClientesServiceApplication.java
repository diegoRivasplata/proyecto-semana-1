package com.proyectosemana1.clientesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientesServiceApplication {
	public static void main(final String[] args) {
		SpringApplication.run(ClientesServiceApplication.class, args);
	}
}
