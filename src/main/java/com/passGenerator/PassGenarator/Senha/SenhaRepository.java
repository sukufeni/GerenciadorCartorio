package com.passGenerator.PassGenarator.Senha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SenhaRepository extends JpaRepository<Senha,Long> {
    void deleteSenhaById(Long id);
}
