package com.passGenerator.PassGenarator.Senha;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table

public class Senha {

    @javax.persistence.Id
    @Id
    @SequenceGenerator(
            name = "Senha_sequence",
            sequenceName = "senha_sequence",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "senha_sequence"
    )

    private Long id;
    private String idPessoa;
    private String protocolo;
    private String cartorio;
    private Categoria categoria;

    public String getProtocolo() {return protocolo;}
    public String getIdPessoa() {return idPessoa;}
    public String getCartorio() {return cartorio;}
    public Long getId(){return id;}
    public com.passGenerator.PassGenarator.Senha.Categoria getCategoria() {return categoria;}

    public Senha() {
    }

    public Senha(Senha senha, Long id) {
        this.id = id;
        this.categoria = senha.getCategoria();
        this.cartorio = senha.getCartorio();
        this.idPessoa = senha.getIdPessoa();
        this.protocolo = senha.getProtocolo();
    }

    public Senha(String protocolo, String cartorio, Categoria categoria, String pessoa) {
        this.protocolo = protocolo;
        this.cartorio = cartorio;
        this.categoria = categoria;
        this.idPessoa = pessoa;
    }
}
