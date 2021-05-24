package com.passGenerator.PassGenarator.Pessoa;

import javax.persistence.*;

@Entity
@Table
public class Pessoa {
    @Id
    @SequenceGenerator(
            name="pessoa_sequence",
            allocationSize = 1,
            initialValue = 1,
            sequenceName = "pessoa_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pessoa_sequence"
    )
    private Long id;
    private String nome;
    private String email; //validar e-mail
    private String telefone;
    private String cpf;

    public String getNome() {return nome;}
    public String getCPF() {return cpf;}

    public Pessoa() {
    }
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Pessoa(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }
}