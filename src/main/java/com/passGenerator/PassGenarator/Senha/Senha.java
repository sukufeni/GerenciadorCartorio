package com.passGenerator.PassGenarator.Senha;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "senha")

public class Senha implements Serializable {

    @javax.persistence.Id
    @Id
    @SequenceGenerator(
            name = "Senha_sequence",
            sequenceName = "senha_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "senha_sequence"
    )

    private Long id;
    private Long idPessoa;
    private Long idProtocolo;
    private Long idCartorio;
    private Categoria categoria;

    public Long getidProtocolo() {return idProtocolo;}
    public Long getIdPessoa() {return idPessoa;}
    public Long getIdCartorio() {return idCartorio;}
    public Long getId(){return id;}
    public com.passGenerator.PassGenarator.Senha.Categoria getCategoria() {return categoria;}

    public Senha() {
    }

    public Senha(Long id, Senha senha, Long idPessoa, Long idProtocolo) {
        this.id = id;
        this.categoria = senha.getCategoria();
        this.idCartorio = senha.getIdCartorio();
        this.idPessoa = idPessoa;
        this.idProtocolo = idProtocolo;
    }

    public Senha(Long idProtocolo, Long idCartorio, Categoria categoria, Long idPessoa) {
        this.idProtocolo = idProtocolo;
        this.idCartorio = idCartorio;
        this.categoria = categoria;
        this.idPessoa = idPessoa;
    }
}
