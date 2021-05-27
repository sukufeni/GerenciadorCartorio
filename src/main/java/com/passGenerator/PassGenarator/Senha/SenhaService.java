package com.passGenerator.PassGenarator.Senha;

import com.passGenerator.PassGenarator.Pessoa.Pessoa;
import com.passGenerator.PassGenarator.Pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SenhaService {

    @Autowired
    private final SenhaRepository repository;
    @Autowired
    private final PessoaRepository pessoaRepository;

    private List<Senha> filaComum = new ArrayList<Senha>();
    private List<Senha> filaPrioritario = new ArrayList<Senha>();
    private Long lastSenha = 1L;

    public SenhaService(SenhaRepository repository, PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.repository = repository;
    }

    public List<Senha> getSenhas() {
        ArrayList<Senha> aux =  new ArrayList<Senha>();
        aux.addAll(filaComum);
        aux.addAll(filaPrioritario);
        return aux;
    }

    public Senha gerarSenha(Senha senha){

        Senha auxSenha = new Senha(senha,lastSenha);

        if (!senha.getCategoria().equals(Categoria.normal)){this.filaPrioritario.add(auxSenha);}
        else{this.filaComum.add(auxSenha);}

        lastSenha+=1L;
        return auxSenha;
    }

    public Senha proximaSenha(){
        Senha retornoSenha;
        if(!this.filaPrioritario.isEmpty())
        {
            retornoSenha = this.filaPrioritario.get(this.filaPrioritario.size()-1);
            filaPrioritario.remove(retornoSenha);
            return retornoSenha;
        }
        else if(!this.filaComum.isEmpty()){
            retornoSenha = this.filaComum.get(this.filaComum.size()-1);
            filaComum.remove(retornoSenha);
            return retornoSenha;
        }
         return null;
    }

    public void DeleteSenhas(){
        this.repository.deleteAll();
    }
}
