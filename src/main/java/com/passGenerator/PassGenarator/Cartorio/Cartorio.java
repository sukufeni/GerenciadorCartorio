package com.passGenerator.PassGenarator.Cartorio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import antlr.collections.List;
import com.passGenerator.PassGenarator.protocolo.TipoProtocolo;

@Entity
@Table
public class Cartorio {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public String getNomeCartorio() {
        return nomeCartorio;
    }

    public String getCodigoCartorio() {
        return codigoCartorio;
    }

    private String nomeCartorio;
    private String codigoCartorio;

    public Cartorio() {
    }

    public Cartorio(String nomeCartorio, String codigoCartorio) {
        this.nomeCartorio = nomeCartorio;
        codigoCartorio = codigoCartorio;
    }

    public Cartorio(Long id, String nomeCartorio, String codigoCartorio) {
        this.id = id;
        this.nomeCartorio = nomeCartorio;
        codigoCartorio = codigoCartorio;
    }
}
