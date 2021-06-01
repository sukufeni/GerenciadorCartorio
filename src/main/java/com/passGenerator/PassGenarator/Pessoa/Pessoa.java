package com.passGenerator.PassGenarator.Pessoa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Pessoa implements Serializable {
    @Id
    @SequenceGenerator(
            name="pessoa_sequence",
            allocationSize = 1,
            sequenceName = "pessoa_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pessoa_sequence"
    )
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    public String getNome() {return nome;}
    public String getCPF() {return cpf;}
    public String getTelefone() {return telefone;}
    public Long getId() {return id;}
    public String getEmail() {return email;}

    public Pessoa() {
    }
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Pessoa(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Pessoa(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }
    public Boolean isEmailValid(){
        return ValidaEmail.isValidEmailAddress(this.email);
    }
    public Boolean isCpfValid(){
        return ValidaCPF.isCPF(this.cpf);
    }
}