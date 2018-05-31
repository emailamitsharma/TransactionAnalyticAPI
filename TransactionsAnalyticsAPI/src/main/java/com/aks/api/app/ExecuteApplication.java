package com.aks.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.aks.api"})
public class ExecuteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExecuteApplication.class, args);
	}
}
