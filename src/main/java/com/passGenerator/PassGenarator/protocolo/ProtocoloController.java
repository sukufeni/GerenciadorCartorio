package com.passGenerator.PassGenarator.protocolo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping(path = "/tipos")
    public ResponseEntity<List<String>> getTipoProtocolos() {
        return new ResponseEntity<>(this.service.getTipoProtocolos(), HttpStatus.OK);
    }

    @PostMapping(path = "/gerar")
    public ResponseEntity<Protocolo> gerarProtocolo(@RequestBody Protocolo protocolo) {
        Protocolo retProtocolo = service.gerarProtocolo(protocolo);
        return new ResponseEntity<>(retProtocolo, HttpStatus.CREATED);
    }

    @PostMapping(path = "/imprimir")
    public ResponseEntity<List<Protocolo>> imprimirProtocolos(@RequestBody HashMap<String, Object> impressaoProtocolo) {
        var idCartorio = impressaoProtocolo.get("idCartorio");
        var dataProtocolo = impressaoProtocolo.get("dataProtocolo");
        if (idCartorio != null && dataProtocolo != null) {
            var ret = this.service.imprimirProtocolos(idCartorio.toString(), Date.valueOf(dataProtocolo.toString()));
            if (!ret.isEmpty()) {
                Optional<Protocolo> ff = ret.stream().filter(o -> o.getCartorio().equals(idCartorio.toString()))
                        .findAny();
                var auxLista = List.of(ff.get());
                return new ResponseEntity<List<Protocolo>>(auxLista, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
