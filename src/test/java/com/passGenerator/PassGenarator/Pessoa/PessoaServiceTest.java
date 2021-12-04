package com.passGenerator.PassGenarator.Pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.Module.SetupContext;
import com.jayway.jsonpath.Option;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PessoaServiceTest.class)
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    private PessoaService pessoaService;
    private Pessoa dummyPessoa;

    @BeforeEach
    void setup() {
        this.pessoaRepository = mock(PessoaRepository.class);
        this.pessoaService = new PessoaService(pessoaRepository);
        this.dummyPessoa = new Pessoa("Dummy", "10525900632", "brunobrandao147@gmail.com", "31992329445");
    }

    @Test
    void canGetPessoas() {
        final Pessoa cloneDummy = new Pessoa("SecondDummy", "10625900632");

        when(pessoaRepository.findAll()).thenReturn(List.of(dummyPessoa, cloneDummy));
        var RetList = pessoaService.GetPessoas();

        verify(pessoaRepository).findAll();
        assertEquals(2, RetList.size());
    }

    @Test
    void canFindPessoaByCpf() {
        when(pessoaRepository.findByCpf(anyString())).thenReturn(Optional.of(dummyPessoa));

        final var retPessoa = pessoaService.getPessoaByCpf(anyString());
        verify(pessoaRepository).findByCpf(anyString());
        assertEquals(dummyPessoa, retPessoa.get());

    }

    @Test
    void cantFindPessoaByCpf() {
        when(pessoaRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        final var retPessoa = pessoaService.getPessoaByCpf(anyString());
        verify(pessoaRepository).findByCpf(anyString());
        assertTrue(retPessoa.isEmpty());

    }

    @Test
    void canAddPessoa() {
        when(pessoaRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(dummyPessoa);
        final Pessoa retPessoa = pessoaService.addPessoa(dummyPessoa);

        verify(pessoaRepository).save(any(Pessoa.class));
        assertEquals(dummyPessoa, retPessoa);
    }

    @Test
    void cantAddPessoaEmailInvalid() {
        
        final Pessoa underTest = new Pessoa("Dummy", "cpf", "email", "telefone");

        when(pessoaRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> {
            pessoaService.addPessoa(underTest);
        }, "e-mail invalido");
        verify(pessoaRepository).findByCpf(anyString());
    }

    @Test
    void cantAddPessoaCpfInvalid() {
        
        final Pessoa underTest = new Pessoa("Dummy", "cpf", "email", "telefone");
        when(pessoaRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        
        assertThrows(IllegalStateException.class, ()->{
            pessoaService.addPessoa(underTest);
        },"CPF invalido");
        verify(pessoaRepository).findByCpf(anyString());
    }
}
