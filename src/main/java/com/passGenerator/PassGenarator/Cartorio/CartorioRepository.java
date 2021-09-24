package com.passGenerator.PassGenarator.Cartorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio,Long> {

    Optional<Cartorio> findCartorioByNomeCartorio(String nome);
    Optional<Cartorio> findById(Long id);

}