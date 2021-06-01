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
        return pessoaCpf;
    }

    public Pessoa addPessoa(Pessoa pessoa) {
        Optional<Pessoa> pessoaCpf = pessoaRepository.findByCpf(pessoa.getCPF());
        if(pessoaCpf.isPresent()){
           return pessoaCpf.get();
        }
        if (!pessoa.isEmailValid()){
            throw new IllegalStateException("e-mail invalido");
        }
        if (!pessoa.isCpfValid()){
            throw new IllegalStateException("CPF invalido");
        }
        return pessoaRepository.save(pessoa);
    }
}
