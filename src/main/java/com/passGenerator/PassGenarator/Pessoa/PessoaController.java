package com.passGenerator.PassGenarator.Pessoa;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path = "/pessoa")
@CrossOrigin(origins = "http://localhost:8080")
public class PessoaController {
    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.service = pessoaService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Pessoa>> getPessoas() {
        return new ResponseEntity<>(this.service.GetPessoas(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/cpf/{cpf}")
    public ResponseEntity<Pessoa> getPessoaByCPF(@PathVariable("cpf") String cpf) {
        Optional<Pessoa> auxPessoa = this.service.getPessoaByCpf(cpf);
        if (auxPessoa.isPresent())
            return new ResponseEntity<>(auxPessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Pessoa> getPessoabyId(@PathVariable("id") Long id) {
        Optional<Pessoa> auxPessoa = this.service.GetPessoa(id);
        if (auxPessoa.isPresent())
            return new ResponseEntity<>(auxPessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/gerar")
    public ResponseEntity<Pessoa> AddPessoa(@RequestBody Pessoa pessoa) {
        Pessoa auxPessoa = this.service.addPessoa(pessoa);
        return new ResponseEntity<>(auxPessoa, HttpStatus.OK);
    }
}
