package com.passGenerator.PassGenarator.protocolo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProtocoloRepository extends JpaRepository<Protocolo,Long> {


}
