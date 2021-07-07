package com.passGenerator.PassGenarator.Senha;

import com.passGenerator.PassGenarator.Pessoa.Pessoa;
import com.passGenerator.PassGenarator.Pessoa.PessoaService;
import org.hibernate.hql.internal.HolderInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/senhas")
public class SenhaController {
    
    private final SenhaService senhaService;

    @Autowired
    public SenhaController(SenhaService senhaService, PessoaService pessoaService) {
        this.senhaService = senhaService;
    }

    @GetMapping("/all")
    public List<Senha> index(String id) {
        return this.senhaService.getSenhas();
    }

    @GetMapping("/proximasenha")
    public ResponseEntity<Senha> proximaSenha() {
        return new ResponseEntity<>(this.senhaService.proximaSenha(), HttpStatus.OK);
    }

    @PostMapping("/gerar")
    public ResponseEntity<Senha> gerarSenha(@RequestBody Senha senha) {
        return new ResponseEntity<>(this.senhaService.gerarSenha(senha), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Senha> deleteSenha(@PathVariable("id") Long senha) {
        if (this.senhaService.deleteSenha(senha)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
