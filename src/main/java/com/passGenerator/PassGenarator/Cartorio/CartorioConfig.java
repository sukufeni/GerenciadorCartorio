package com.passGenerator.PassGenarator.Cartorio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class CartorioConfig {

    @Bean
    CommandLineRunner commandLineRunner(CartorioRepository repository) {
// Convert into yml file
        return args -> {
            Cartorio notas = new Cartorio(Long.parseLong("1"), "Tabelionato de Notas", "01.508-1/0867-");
            Cartorio Titulos = new Cartorio(Long.parseLong("2"), "Protesto de Titulos", "01.508-1/0867-");
            Cartorio RegistroCivil = new Cartorio(Long.parseLong("3"), "Registro Civil", "00.853-2/0866-");
            repository.saveAll(List.of(notas, Titulos, RegistroCivil));
        };
    }
}
