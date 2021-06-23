package com.passGenerator.PassGenarator.Senha;

import com.passGenerator.PassGenarator.Pessoa.Pessoa;
import com.passGenerator.PassGenarator.Pessoa.PessoaRepository;
import com.passGenerator.PassGenarator.Pessoa.PessoaService;
import com.passGenerator.PassGenarator.protocolo.Protocolo;
import com.passGenerator.PassGenarator.protocolo.ProtocoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

@Service
public class SenhaService {

    @Autowired
    private final SenhaRepository repository;

    @Autowired
    private final PessoaService pessoaService;

    @Autowired
    private final ProtocoloService protocoloService;


    private List<Senha> filaComum = new ArrayList<Senha>();
    private List<Senha> filaPrioritario = new ArrayList<Senha>();
    private Long lastSenha = 1L;

    public SenhaService(SenhaRepository repository, PessoaService pessoaService, ProtocoloService protocoloService) {
        this.pessoaService = pessoaService;
        this.repository = repository;
        this.protocoloService = protocoloService;
    }

    public List<Senha> getSenhas() {
        ArrayList<Senha> aux =  new ArrayList<Senha>();
        aux.addAll(filaComum);
        aux.addAll(filaPrioritario);
        return aux;
    }

    public void deleteSenha(Long id){
        this.repository.deleteSenhaById(id);
    }

    public Senha gerarSenha(Senha senha){
        //geração de senhas
        Senha auxSenha = new Senha(lastSenha,senha,senha.getIdPessoa(), senha.getidProtocolo());
        if (!senha.getCategoria().equals(Categoria.normal)){this.filaPrioritario.add(auxSenha);}
        else{this.filaComum.add(auxSenha);}

        lastSenha+=1L;
        return auxSenha;
    }

    public Senha proximaSenha(){
        Senha retornoSenha;
        if(!this.filaPrioritario.isEmpty())
        {
            retornoSenha = this.filaPrioritario.get(0);
            filaPrioritario.remove(retornoSenha);
            return retornoSenha;
        }
        else if(!this.filaComum.isEmpty()){
            retornoSenha = this.filaComum.get(0);
            filaComum.remove(retornoSenha);
            return retornoSenha;
        }
         return null;
    }

    public void DeleteSenhas(){
        this.repository.deleteAll();
    }
}
