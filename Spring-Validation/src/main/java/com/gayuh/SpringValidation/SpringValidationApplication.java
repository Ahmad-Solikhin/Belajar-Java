package com.gayuh.SpringValidation;

import com.gayuh.SpringValidation.properties.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		DatabaseProperties.class
})
public class SpringValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringValidationApplication.class, args);
	}

}
