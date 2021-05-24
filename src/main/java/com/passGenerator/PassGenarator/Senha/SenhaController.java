package com.passGenerator.PassGenarator.Senha;

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
    public SenhaController(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

//    @GetMapping("/")
//    public String homePage(Model model) {
//        model.addAttribute("appName", appName);
//        return "home";

//    }
    @GetMapping("/")
    public List<Senha> index(String id) {return this.senhaService.getSenhas();}

    @PostMapping
    public Senha gerarSenha(@RequestBody Senha senha){return this.senhaService.gerarSenha(senha);}
}
