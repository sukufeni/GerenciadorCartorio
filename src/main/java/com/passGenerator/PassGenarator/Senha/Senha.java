package com.passGenerator.PassGenarator.Senha;

import com.passGenerator.PassGenarator.Pessoa.Pessoa;
import com.passGenerator.PassGenarator.protocolo.Protocolo;
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
    private String pessoa ;
    private String protocolo;
    private String cartorio;
    private Categoria categoria;

    public String getProtocolo() {return protocolo;}
    public String getPessoa() {return pessoa;}
    public String getCartorio() {return cartorio;}
    public com.passGenerator.PassGenarator.Senha.Categoria getCategoria() {return categoria;}

    public Senha() {
    }

    public Senha(String protocolo, String cartorio, Categoria categoria, String pessoa) {
        this.protocolo = protocolo;
        this.cartorio = cartorio;
        this.categoria = categoria;
        this.pessoa = pessoa;
    }
}
