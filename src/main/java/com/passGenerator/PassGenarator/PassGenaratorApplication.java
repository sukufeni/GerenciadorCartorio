package com.passGenerator.PassGenarator;

import com.passGenerator.PassGenarator.Pessoa.Pessoa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class PassGenaratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassGenaratorApplication.class, args);
	}


}
