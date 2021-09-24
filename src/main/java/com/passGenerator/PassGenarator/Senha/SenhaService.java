package com.passGenerator.PassGenarator.Senha;

import com.passGenerator.PassGenarator.Pessoa.PessoaService;

import com.passGenerator.PassGenarator.protocolo.ProtocoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private Senha currSenha;

    public SenhaService(SenhaRepository repository, PessoaService pessoaService, ProtocoloService protocoloService) {
        this.pessoaService = pessoaService;
        this.repository = repository;
        this.protocoloService = protocoloService;
    }

    public List<Senha> getSenhas() {
        ArrayList<Senha> aux = new ArrayList<Senha>();
        aux.addAll(filaComum);
        aux.addAll(filaPrioritario);
        return aux;
    }

    public Boolean deleteSenha(Long id) {
        Optional<Senha> auxComum = this.filaComum.stream().filter(o -> o.getId().equals(id)).findFirst();
        Optional<Senha> auxPrioritaria = this.filaPrioritario.stream().filter(o -> o.getId().equals(id)).findFirst();
        if (auxComum.isPresent()) {
            return this.filaComum.remove(auxComum.get());
        } else if (auxPrioritaria.isPresent()) {
            return this.filaPrioritario.remove(auxPrioritaria.get());
        }
        return false;
    }

    public Senha gerarSenha(Senha senha) {
        // geração de senhas
        Senha auxSenha = new Senha(lastSenha, senha, senha.getIdPessoa(), senha.getidProtocolo());
        if (!senha.getCategoria().equals(Categoria.normal)) {
            this.filaPrioritario.add(auxSenha);
        } else {
            this.filaComum.add(auxSenha);
        }

        lastSenha += 1L;
        return auxSenha;
    }

    public void proximaSenha() {
        if (!this.filaPrioritario.isEmpty()) {
            this.currSenha = this.filaPrioritario.get(0);
            filaPrioritario.remove(this.currSenha);

        } else if (!this.filaComum.isEmpty()) {
            this.currSenha = this.filaComum.get(0);
            filaComum.remove(this.currSenha);
        }
    }

    public Senha currSenha() {
        return this.currSenha == null ? null : this.currSenha;
    }

    public void DeleteSenhas() {
        this.repository.deleteAll();
    }
}
