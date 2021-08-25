package com.passGenerator.PassGenarator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class PassGenaratorApplication {
	@GetMapping("/")
 	public String login(){
 		return "authenticated successfully" ;
 	}
	public static void main(String[] args) {
		SpringApplication.run(PassGenaratorApplication.class, args);
	}
}
