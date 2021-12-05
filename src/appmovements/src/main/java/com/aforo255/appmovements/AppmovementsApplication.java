package com.aforo255.appmovements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppmovementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppmovementsApplication.class, args);
	}

}
