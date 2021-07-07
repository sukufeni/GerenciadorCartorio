package com.passGenerator.PassGenarator.protocolo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import antlr.collections.List;

import java.lang.StackWalker.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProtocoloRepository extends JpaRepository<Protocolo, Long> {

    public Optional<Protocolo> findProtocoloByTitularProtocolo(String idPessoa);

    public Optional<Protocolo> findProtocoloByQualidadeProtocolo(String qualidadeProtocolo);

    public Optional<Protocolo> findProtocoloById(Long id);

    public Optional<ArrayList<Protocolo>> findByDataCriacao(LocalDate dataCriacao);
}
