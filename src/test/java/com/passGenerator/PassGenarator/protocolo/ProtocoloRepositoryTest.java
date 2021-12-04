package com.passGenerator.PassGenarator.protocolo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProtocoloRepositoryTest {

    @Autowired
    private ProtocoloRepository pRepository;

    private Protocolo dummyProtocolo = new Protocolo(LocalDate.now(), 2L, 2L, "Testamento");

    @Test
    void findProtocoloByQualidadeProtocolo() {
        // given
        assertNotNull(pRepository);
        pRepository.save(dummyProtocolo);

        // when
        Protocolo retProtocolo = pRepository.findProtocoloByQualidadeProtocolo(dummyProtocolo.getQualidadeProtocolo());

        // then
        assertNotNull(retProtocolo);
    }

    @Test
    void findProtocoloByTitularProtocolo() {
        // given
        assertNotNull(pRepository);
        pRepository.save(dummyProtocolo);
        
        // when
        Optional retProtocolo = pRepository.findProtocoloByTitularProtocolo(dummyProtocolo.getTitularProtocolo());

        // then
        assertTrue(retProtocolo.isPresent());
    }

    @Test
    void findProtocoloByDataCriacao() {
        // given
        assertNotNull(pRepository);
        pRepository.save(dummyProtocolo);
        // when
        Optional retoProtocolo = pRepository.findByDataCriacao(dummyProtocolo.getDataCriacao());

        // then
        assertTrue(retoProtocolo.isPresent());
    }

    @Test
    void findProtocolosActive() {
        // given
        assertNotNull(pRepository);
        List<Protocolo> dummies = List.of(dummyProtocolo, dummyProtocolo, dummyProtocolo);
        pRepository.saveAll(dummies);

        // when
        List<Protocolo> rProtocolos = pRepository.findProtocolosActive();
        
        // then
        assertFalse(rProtocolos.isEmpty());
    }
}
