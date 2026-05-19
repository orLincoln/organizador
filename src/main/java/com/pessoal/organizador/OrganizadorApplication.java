package com.pessoal.organizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrganizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizadorApplication.class, args);

		
	}


}
