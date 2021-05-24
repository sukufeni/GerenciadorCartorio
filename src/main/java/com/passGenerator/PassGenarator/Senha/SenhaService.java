package com.passGenerator.PassGenarator.Senha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SenhaService {
    @Autowired
    private final SenhaRepository repository;

    public SenhaService(SenhaRepository repository) {
        this.repository = repository;
    }

    public List<Senha> getSenhas() {
        return this.repository.findAll();
    }

    public Senha gerarSenha(Senha senha){
        return this.repository.save(senha);
    }

    public Senha proximaSenha(){
        int posicaoProximaSenha = 1;
        Optional<Senha> aux = Optional.ofNullable(this.repository.findAll().get(posicaoProximaSenha));
        if(!aux.isPresent()) throw new IllegalStateException("Senha não encontrada!");

        return aux.get();
    }

    public void DeleteSenhas(){
        this.repository.deleteAll();
    }
}
