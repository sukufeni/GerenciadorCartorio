package com.passGenerator.PassGenarator.protocolo;

import com.passGenerator.PassGenarator.Pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProtocoloService {
    @Autowired
    private final ProtocoloRepository repository;

    @Autowired
    private final PessoaRepository pessoaRepository;

    public ProtocoloService(ProtocoloRepository repository, PessoaRepository pessoaRepository) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<Protocolo> getProtocolos(){
        return this.repository.findAll();
    }
    public Optional<Protocolo> getProtocolobyId(Long id){
        return this.repository.findProtocoloById(id);
    }

    public Protocolo gerarProtocolo(Protocolo protocolo){
        Protocolo auxProtocolo = new Protocolo(protocolo);
        return this.repository.save(auxProtocolo);
    }
    public Protocolo getProtocoloByPessoa(String idPessoa){
        Optional<Protocolo> protocolo = this.repository.findProtocoloByTitularProtocolo(idPessoa);
        if(!protocolo.isPresent()) throw  new IllegalStateException("Protocolo não encontrado!");
        return protocolo.get();
    }

    public Protocolo getProtocoloByQualidade(String qualidadeProtocolo){
        Optional<Protocolo> protocolo = this.repository.findProtocoloByQualidadeProtocolo(qualidadeProtocolo);
        if(!protocolo.isPresent()) throw  new IllegalStateException("Protocolo não encontrado!");
        return protocolo.get();
    }
}
