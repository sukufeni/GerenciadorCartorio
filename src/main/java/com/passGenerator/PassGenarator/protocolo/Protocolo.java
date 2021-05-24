package com.passGenerator.PassGenarator.protocolo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Protocolo {

    @SequenceGenerator(
        name = "protocolo_sequence",
        sequenceName = "protocolo_sequence",
        initialValue = 1,
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "protocolo_sequence"
    )

    @Id
    private Long id;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    private LocalDate dataCriacao;
    private LocalDate dataEntrega;
    private String cartorio; // Deve ser a classe Cartorio no fim.
    private String titularProtocolo; // Deve ser a classe Pessoa no fim.

    public LocalDate getDataCriacao() {return dataCriacao;}
    public LocalDate getDataEntrega() {return dataEntrega;}
    public String getCartorio() {return cartorio;}

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
    }

    public String getTitularProtocolo() {return titularProtocolo;}

    public Protocolo() {

    }

    public Protocolo(LocalDate dataEntrega, String titularProtocolo, String cartorio) {
        this.dataCriacao = LocalDate.now();
        this.dataEntrega = dataEntrega;
        this.titularProtocolo = titularProtocolo;
        this.cartorio = cartorio;
    }
}
