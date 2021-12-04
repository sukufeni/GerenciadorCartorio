package com.passGenerator.PassGenarator.protocolo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.passGenerator.PassGenarator.Cartorio.CartorioRepository;
import com.passGenerator.PassGenarator.Pessoa.PessoaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProtocoloServiceTest.class)
@ExtendWith(MockitoExtension.class)
public class ProtocoloServiceTest {

    @Mock
    ProtocoloRepository pRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    CartorioRepository cartorioRepository;

    ProtocoloService pService;
    private final Protocolo dummyProtocolo = new Protocolo(LocalDate.now(), 2L, 2L, "Testamento");

    @BeforeEach
    void setup() {
        pService = new ProtocoloService(pRepository, pessoaRepository, cartorioRepository);
    }

    @Test
    void canGerarProtocolo() {
        // when
        Protocolo ret = pService.gerarProtocolo(dummyProtocolo);

        // then expected to be ran
        verify(pRepository).save(any());

    }

    @Test
    void canImprimirProtocolos() {
        // given
        ArrayList<Protocolo> testList = new ArrayList<Protocolo>();
        testList.addAll(List.of(new Protocolo(LocalDate.of(2021, 2, 1), 1L, 1L, "Testamento"),
                new Protocolo(LocalDate.of(2020, 2, 1), 2L, 2L, "Testamento"), dummyProtocolo));
        final Optional<ArrayList<Protocolo>> underTest = Optional.of(testList);

        // when
        when(pRepository.findByDataCriacao(any())).thenReturn(underTest);
        final List<Protocolo> ret = pService.imprimirProtocolos("2");

        // then
        // should be called
        verify(pRepository).findByDataCriacao(any());

        // valid from the given parameter and mock
        assertEquals(2, ret.size());
    }

    @Test
    void canTImprimirProtocolos() {
        // given
        final Optional<ArrayList<Protocolo>> underTest = Optional.empty();

        // when
        when(pRepository.findByDataCriacao(any())).thenReturn(underTest);

        // then
        // Expected to be an error
        assertThrows(NoSuchElementException.class, () -> {
            pService.imprimirProtocolos("2");
        }, "Erro ao encontrar Protocolos para a data Selecionada!");

        // expected to be called anyway
        verify(pRepository).findByDataCriacao(any());
    }

    @Test
    void canGetProtocolos() {
        pService.getProtocolos();
        verify(pRepository).findProtocolosActive();
    }

    @Test
    void canGetTiposProtocolos() {
        var ret = pService.getTipoProtocolos();

        // should return a list with the types
        assertFalse(ret.isEmpty());
    }

    @Test
    void ExceptionFindprotocoloByQualidade() {

        assertThrows(IllegalStateException.class, () -> {
            pService.getProtocoloByQualidade("abladuas");
        },
                "Protocolo não Encontrado");
    }

    @Test
    void findprotocoloByQualidade() {

        // when
        when(pRepository.findProtocoloByQualidadeProtocolo(any())).thenReturn(dummyProtocolo);
        pService.getProtocoloByQualidade("Testamento");

        // then
        verify(pRepository).findProtocoloByQualidadeProtocolo(any());
    }

    @Test
    void findProtocoloByPessoa(){
        // when
        when(pRepository.findProtocoloByTitularProtocolo(anyLong())).thenReturn(Optional.of(dummyProtocolo));
        final Protocolo ret = pService.getProtocoloByPessoa("2");

        // then
        verify(pRepository).findProtocoloByTitularProtocolo(anyLong());
        assertEquals(ret, dummyProtocolo);


    }
}
