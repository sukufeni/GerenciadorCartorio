package com.passGenerator.PassGenarator.protocolo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProtocoloService {
    private final ProtocoloRepository repository;

    public ProtocoloService(ProtocoloRepository repository) {
        this.repository = repository;
    }

    public List<Protocolo> getProtocolos(){
        return this.repository.findAll();
    }

    public Protocolo gerarProtocolo(Protocolo protocolo){
        protocolo.setDataCriacao();
        return this.repository.save(protocolo);
    }
    public Protocolo getProtocoloByPessoa(String pessoa){
        Optional<Protocolo> protocolo = this.repository.findById(Long.parseLong("1"));

        if(!protocolo.isPresent()) throw  new IllegalStateException("Protocolo não encontrado!");
        return protocolo.get();
    }
}
