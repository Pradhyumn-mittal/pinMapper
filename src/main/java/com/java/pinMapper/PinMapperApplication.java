package com.java.pinMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.java.pinMapper.configuration")
public class PinMapperApplication {
	public static void main(String[] args) {
		SpringApplication.run(PinMapperApplication.class, args);
	}
}