  package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class CampingSpotMainApplication {
 
	@Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    } 
	
	public static void main(String[] args) {
		SpringApplication.run(CampingSpotMainApplication.class, args);
	}	
} 