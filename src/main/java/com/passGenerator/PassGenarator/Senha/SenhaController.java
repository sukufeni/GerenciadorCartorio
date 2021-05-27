package com.passGenerator.PassGenarator.Senha;

import com.passGenerator.PassGenarator.Pessoa.Pessoa;
import com.passGenerator.PassGenarator.Pessoa.PessoaService;
import org.hibernate.hql.internal.HolderInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController()
public class SenhaController {
    @Value("${spring.application.name}")
    String appName;

    private final SenhaService senhaService;

    @Autowired
    public SenhaController(SenhaService senhaService, PessoaService pessoaService) {

        this.senhaService = senhaService;
    }

//    @GetMapping("/")
//    public String homePage(Model model) {
//        model.addAttribute("appName", appName);
//        return "home";

//    }
    @GetMapping("/")
    public List<Senha> index(String id) {return this.senhaService.getSenhas();}

    @GetMapping("/senha/")
    public Senha proximaSenha() {return this.senhaService.proximaSenha();}

    @PostMapping
    public Senha gerarSenha(@RequestBody Senha senha){
        return this.senhaService.gerarSenha(senha);
    }
}
