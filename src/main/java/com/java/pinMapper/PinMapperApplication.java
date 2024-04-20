package com.java.pinMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.java.pinMapper")
@EnableSwagger2
public class PinMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinMapperApplication.class, args);
	}

}
