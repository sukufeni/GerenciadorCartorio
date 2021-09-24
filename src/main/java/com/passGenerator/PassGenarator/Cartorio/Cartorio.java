package com.passGenerator.PassGenarator.Cartorio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Cartorio {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public String getNomeCartorio() {
        return this.nomeCartorio;
    }

    public String getCodigoCartorio() {
        return this.codigoCartorio;
    }

    private String nomeCartorio;
    private String codigoCartorio;

    public Cartorio() {
    }

    public Cartorio(String nomeCartorio, String codigoCartorio) {
        this.nomeCartorio = nomeCartorio;
        this.codigoCartorio = codigoCartorio;
    }

    public Cartorio(Long id, String nomeCartorio, String codigoCartorio) {
        this.id = id;
        this.nomeCartorio = nomeCartorio;
        this.codigoCartorio = codigoCartorio;
    }
}
