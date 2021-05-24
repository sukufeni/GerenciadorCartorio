package com.passGenerator.PassGenarator.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/pessoa")
public class PessoaController {
    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.service = pessoaService;
    }

    @GetMapping
    public List<Pessoa> getPessoas(){
        return this.service.GetPessoas();
    }



    @PostMapping
    public void AddPessoa(@RequestBody Pessoa pessoa){
        this.service.addPessoa(pessoa);
    }
}
