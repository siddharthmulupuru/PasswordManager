package com.siddharthmulupuru.passwordmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Run with dev profile on machine: ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
/*
Register: curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"siddh","password":"mydog123"}'

Create entries:
curl -X POST http://localhost:8080/api/vault \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_TOKEN" \
-d '{"encryptedName":"Gmail","encryptedWebsiteUsername":"siddh@gmail.com","encryptedWebsitePassword":"gmail123","encryptedWebsite":"gmail.com","encryptedDescription":"Personal email"}'

curl -X POST http://localhost:8080/api/vault \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_TOKEN" \
-d '{"encryptedName":"GitHub","encryptedWebsiteUsername":"siddharthmulupuru","encryptedWebsitePassword":"github123","encryptedWebsite":"github.com"}'

curl -X POST http://localhost:8080/api/vault \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_TOKEN" \
-d '{"encryptedName":"Amazon","encryptedWebsiteUsername":"siddh@gmail.com","encryptedWebsitePassword":"amazon123","encryptedWebsite":"amazon.com"}'
 */

@SpringBootApplication
public class PasswordmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordmanagerApplication.class, args);
	}

}
