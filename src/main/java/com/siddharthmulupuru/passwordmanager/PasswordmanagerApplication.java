package com.siddharthmulupuru.passwordmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Run with dev profile on machine: ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
/*
Register:
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"test","password":"test123!"}'
 */

@SpringBootApplication
public class PasswordmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordmanagerApplication.class, args);
	}

}
