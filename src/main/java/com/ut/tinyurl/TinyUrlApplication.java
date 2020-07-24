package com.ut.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
public class TinyUrlApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TinyUrlApplication.class, args);
	}

}
