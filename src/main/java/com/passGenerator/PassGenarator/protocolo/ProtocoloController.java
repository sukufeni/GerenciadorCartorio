package com.passGenerator.PassGenarator.protocolo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/protocolo")
@CrossOrigin(origins = "*")
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

    @PutMapping(path = "/disable")
    public ResponseEntity<Boolean> deleteProtocolo(@RequestBody HashMap<String, Object> protocolo) {
        Long idProtocolo = Long.parseLong(protocolo.get("idProtocolo").toString());
        String motivo = protocolo.get("motivo").toString();

        boolean result = this.service.disableProtocolo(idProtocolo, motivo);
        return result ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/imprimir")
    public ResponseEntity<Object> imprimirProtocolos(@RequestBody HashMap<String, Object> impressaoProtocolo) {
        var idCartorio = impressaoProtocolo.get("idCartorio");
        if (idCartorio != null) {
            var ret = this.service.imprimirProtocolos(idCartorio.toString());
            return new ResponseEntity<Object>(ret, HttpStatus.OK);
        }
        // Error on request itself
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/imprimir/detalhado")
    public ResponseEntity<InputStreamResource> imprimirProtocoloDetalhado(
            @RequestBody HashMap<String, Object> impressaoProtocolo) {
        var idProtocolo = impressaoProtocolo.get("idProtocolo");

        if (idProtocolo != null) {
            ByteArrayInputStream ret = this.service.imprimirProtocoloDetalhado(idProtocolo.toString());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(ret));
        }
        // Error on request itself
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
