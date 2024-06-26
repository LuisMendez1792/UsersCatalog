package com.tcs.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.tcs.users.repository")
@ComponentScan(basePackages = "com.tcs.users")
@EntityScan("com.tcs.users.model")
public class UsersCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersCatalogApplication.class, args);
	}

}
