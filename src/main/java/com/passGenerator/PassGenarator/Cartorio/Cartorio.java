package com.passGenerator.PassGenarator.Cartorio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Cartorio {

    @Id
    private Long id;
    private String nomeCartorio;
    private String IdentificacaoCartorio;


    public Cartorio() {
    }

    public Cartorio(String nomeCartorio, String identificacaoCartorio) {
        this.nomeCartorio = nomeCartorio;
        IdentificacaoCartorio = identificacaoCartorio;
    }

    public Cartorio(Long id, String nomeCartorio, String identificacaoCartorio) {
        this.id = id;
        this.nomeCartorio = nomeCartorio;
        IdentificacaoCartorio = identificacaoCartorio;
    }
}
