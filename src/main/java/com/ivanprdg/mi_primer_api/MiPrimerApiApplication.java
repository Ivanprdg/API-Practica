package com.ivanprdg.mi_primer_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Mi Primer API", version = "1.0", description = "Demo API"))
public class MiPrimerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiPrimerApiApplication.class, args);
	}

}
