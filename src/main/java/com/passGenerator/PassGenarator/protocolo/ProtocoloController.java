package com.passGenerator.PassGenarator.protocolo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/protocolo")
public class ProtocoloController {

    private final ProtocoloService service;

    @Autowired
    public ProtocoloController(ProtocoloService service) {
        this.service = service;
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Protocolo> getProtocolo(@PathVariable("id") String id) {
        Protocolo protocolo = service.getProtocolobyId(Long.parseLong(id)).get();
        return new ResponseEntity<>(protocolo, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Protocolo>> getProtocolos() {
        return new ResponseEntity<>(service.getProtocolos(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/pessoa/{idPessoa}")
    public Protocolo getProtocolobyPessoa(@PathVariable("idPessoa") String idPessoa) {
        return service.getProtocolos().get((int) Long.parseLong(idPessoa));
    }

    @PostMapping(path = "/gerar")
    public ResponseEntity<Protocolo> gerarProtocolo(@RequestBody Protocolo protocolo) {
        Protocolo retProtocolo = service.gerarProtocolo(protocolo);
        return new ResponseEntity<>(retProtocolo, HttpStatus.CREATED);
    }
}
