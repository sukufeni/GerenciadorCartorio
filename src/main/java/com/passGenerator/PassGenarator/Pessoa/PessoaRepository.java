package com.passGenerator.PassGenarator.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    @Query("SELECT s FROM Pessoa s WHERE s.cpf = ?1")
    Optional<Pessoa> findByCpf(String cpf);
}
