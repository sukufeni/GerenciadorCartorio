package com.passGenerator.PassGenarator.protocolo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import com.passGenerator.PassGenarator.Cartorio.CartorioRepository;
import com.passGenerator.PassGenarator.Pessoa.PessoaRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    AutoCloseable auto;
    Protocolo dummyProtocolo = new Protocolo(LocalDate.now(), 2L, 2L, "Testamento");

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
    void canGetProtocolos() {
        pService.getProtocolos();
        verify(pRepository).findProtocolosActive();
    }

    @Test
    void canFetchTiposProtocolos() {
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
}
