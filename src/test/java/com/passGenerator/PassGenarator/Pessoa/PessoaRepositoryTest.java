package com.passGenerator.PassGenarator.Pessoa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    PessoaRepository pessoaRepository;
    Pessoa auxPessoa = new Pessoa("TestPerson", "10525900632");

    @Test
    void findByCpfTest() {
        // given
        pessoaRepository.save(auxPessoa);
        // When
        assertTrue(pessoaRepository.findAll().size() > 0);
        Optional pessoa = pessoaRepository.findByCpf("10525900632");
        // Then
        assertTrue(pessoa.isPresent());
    }
}
