package com.userhub.userhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.userhub.userhub.infra.schemas")
@EnableJpaRepositories(basePackages = "com.userhub.userhub.infra.repository")
public class UserHubApplication {
	public static void main(String[] args) {

		// Run Spring Boot application
		SpringApplication.run(UserHubApplication.class, args);
	}

}
