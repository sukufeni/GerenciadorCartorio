package com.passGenerator.PassGenarator.protocolo;

import org.apache.tomcat.util.net.openssl.ciphers.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/protocolo")
public class ProtocoloController {
    private final ProtocoloService service;

    @Autowired
    public ProtocoloController(ProtocoloService service) {
        this.service = service;
    }

    @GetMapping
    public List<Protocolo> getProtocolos(){
        return service.getProtocolos();
    }

    @GetMapping(path = "/{id}}")
    public Protocolo getProtocolo(String id){
        return  service.getProtocolos().get((int) Long.parseLong(id));
    }

    @PostMapping
    public Protocolo gerarProtocolo(@RequestBody Protocolo protocolo){
        return service.gerarProtocolo(protocolo);
    }
}
