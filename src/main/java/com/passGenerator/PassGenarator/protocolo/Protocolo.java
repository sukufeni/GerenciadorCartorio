package com.passGenerator.PassGenarator.protocolo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Stack;

@Entity
@Table
public class Protocolo implements Serializable {

    @SequenceGenerator(name = "protocolo_sequence", sequenceName = "protocolo_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "protocolo_sequence")

    @Id
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getExcluido(){
        return this.excluido;
    }

    private LocalDate dataCriacao;
    private LocalDate dataEntrega;
    private String qualidadeProtocolo;
    private long cartorio;
    private Long titularProtocolo;
    private Boolean excluido;
    private String observacao;

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public Long getCartorio() {
        return cartorio;
    }

    public String getQualidadeProtocolo() {
        return qualidadeProtocolo;
    }

    public Long getTitularProtocolo() {
        return titularProtocolo;
    }

    public Protocolo() {
    }

    public Protocolo(Protocolo entrada, Long cartorio) {
        this.dataCriacao = LocalDate.now();
        this.dataEntrega = entrada.dataEntrega;
        this.titularProtocolo = entrada.getTitularProtocolo();
        this.cartorio = cartorio;
        this.qualidadeProtocolo = entrada.qualidadeProtocolo;
        this.excluido = false;
    }

    public Protocolo(LocalDate dataEntrega, Long titularProtocolo, Long cartorio, String qualidadeProtocolo) {
        this.dataCriacao = LocalDate.now();
        this.excluido = false;
        this.dataEntrega = dataEntrega;
        this.titularProtocolo = titularProtocolo;
        this.cartorio = cartorio;
        this.qualidadeProtocolo = qualidadeProtocolo;
    }

    public Boolean disableProtocolo(String observacao) {
        this.excluido = true;
        this.observacao = observacao;
        return this.excluido;
    }
}
