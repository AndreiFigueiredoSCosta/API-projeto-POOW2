package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API-projeto-POOW2",
				version = "1.0",
				description = "Trabalho da disciplina de programação orientada a objetos pra web 2",
				contact = @Contact(
						name = "Andrei Figueiredo da Silva Costa",
						email = "andreifscosta@gmail.com"
				)
		)
)
public class ApiDoProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDoProjetoApplication.class, args);
	}

}
