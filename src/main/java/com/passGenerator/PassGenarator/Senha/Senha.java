package com.passGenerator.PassGenarator.Senha;

import com.passGenerator.PassGenarator.Pessoa.Pessoa;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table

public class Senha implements Serializable {

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
    private Long idPessoa;
    private Long idProtocolo;
    private Long idCartorio;
    private Categoria categoria;

    public Long getProtocolo() {return idProtocolo;}
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
        this.idProtocolo = senha.getProtocolo();
    }

    public Senha(Long idProtocolo, Long idCartorio, Categoria categoria, Long idPessoa) {
        this.idProtocolo = idProtocolo;
        this.idCartorio = idCartorio; //resolve
        this.categoria = categoria;
        this.idPessoa = idPessoa;
    }
}
