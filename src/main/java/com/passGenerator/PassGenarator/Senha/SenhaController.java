package com.passGenerator.PassGenarator.Senha;

import com.passGenerator.PassGenarator.Pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/senhas")
@CrossOrigin(origins = "*")
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

    @GetMapping("/currentsenha")
    public ResponseEntity<Senha> getProximaSenha() {
        return new ResponseEntity<>(this.senhaService.currSenha(), HttpStatus.OK);
    }
    @GetMapping("/proximasenha")
    public ResponseEntity<Senha> proximaSenha() {
        this.senhaService.proximaSenha();
        //add alarm
        return new ResponseEntity<>(HttpStatus.OK);
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
