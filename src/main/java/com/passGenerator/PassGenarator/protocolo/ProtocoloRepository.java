package com.passGenerator.PassGenarator.protocolo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Repository
public interface ProtocoloRepository extends JpaRepository<Protocolo, Long> {

    public Optional<Protocolo> findProtocoloByTitularProtocolo(String idPessoa);

    public Optional<Protocolo> findProtocoloByQualidadeProtocolo(String qualidadeProtocolo);

    public Optional<Protocolo> findProtocoloById(Long id);

    @Query("select p from Protocolo p where p.excluido = false")
    public List<Protocolo> findProtocolosActive();

    public Optional<ArrayList<Protocolo>> findByDataCriacao(LocalDate dataCriacao);
}
