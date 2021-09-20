package com.optum.SmartProcidurePredictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.optum.SmartProcidurePredictor"})
public class SmartProcidurePredictorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartProcidurePredictorApplication.class, args);
	}

}
