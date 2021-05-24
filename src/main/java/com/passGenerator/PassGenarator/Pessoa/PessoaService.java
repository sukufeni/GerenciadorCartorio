package com.passGenerator.PassGenarator.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> GetPessoas()
    {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getPessoaByCpf(String cpf){
        Optional<Pessoa> pessoaCpf = pessoaRepository.findByCpf(cpf);
        if(pessoaCpf.isPresent()){
            throw  new IllegalStateException("Pessoa existente");
        }
        return pessoaCpf;
    }

    public void addPessoa(Pessoa pessoa) {
        Optional<Pessoa> pessoaCpf = pessoaRepository.findByCpf(pessoa.getCPF());
        if(pessoaCpf.isPresent()){
           throw  new IllegalStateException("Pessoa existente");
        }
        pessoaRepository.save(pessoa);
    }
}
