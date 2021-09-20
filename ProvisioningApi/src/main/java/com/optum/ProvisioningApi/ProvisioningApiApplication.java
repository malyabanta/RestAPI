package com.optum.ProvisioningApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.optum.ProvisioningApi"})
public class ProvisioningApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvisioningApiApplication.class, args);
	}

}
