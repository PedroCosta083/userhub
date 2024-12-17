package com.formbuilder.formbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.formbuilder.formbuilder.domain.entities")
@EnableJpaRepositories(basePackages = "com.formbuilder.formbuilder.infra.repository")
public class FormbuilderApplication {
	public static void main(String[] args) {

		// Run Spring Boot application
		SpringApplication.run(FormbuilderApplication.class, args);
	}

}
