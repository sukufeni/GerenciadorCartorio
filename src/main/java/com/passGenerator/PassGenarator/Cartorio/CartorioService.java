package com.passGenerator.PassGenarator.Cartorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartorioService {

    private final CartorioRepository repository;

    @Autowired
    public CartorioService(CartorioRepository repository) {
        this.repository = repository;
    }
}
