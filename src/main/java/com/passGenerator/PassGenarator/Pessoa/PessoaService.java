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

    public Optional<Pessoa> GetPessoa(Long id)
    {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa;
    }

    public Optional<Pessoa> getPessoaByCpf(String cpf){
        Optional<Pessoa> pessoaCpf = pessoaRepository.findByCpf(cpf);
        return pessoaCpf;
    }

    public Pessoa addPessoa(Pessoa pessoa) {
        Optional<Pessoa> retPessoa = pessoaRepository.findByCpf(pessoa.getCPF());
        if(retPessoa.isPresent()){
           return retPessoa.get();
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
