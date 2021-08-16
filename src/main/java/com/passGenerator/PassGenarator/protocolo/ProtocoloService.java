package com.passGenerator.PassGenarator.protocolo;

import com.passGenerator.PassGenarator.Pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProtocoloService {
    @Autowired
    private final ProtocoloRepository repository;

    @Autowired
    private final PessoaRepository pessoaRepository;

    public ProtocoloService(ProtocoloRepository repository, PessoaRepository pessoaRepository) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<Protocolo> getProtocolos() {
        return this.repository.findAll();
    }

    public List<Protocolo> imprimirProtocolos(String idCartorio, Date dataProtocolo) {
        return this.repository.findByDataCriacao(dataProtocolo.toLocalDate()).isEmpty() ? new ArrayList<>()
                : this.repository.findByDataCriacao(dataProtocolo.toLocalDate()).get();
    }

    public Optional<Protocolo> getProtocolobyId(Long id) {
        return this.repository.findProtocoloById(id);
    }

    public List<String> getTipoProtocolos() {
        List<String> auxKeysProtocolos = new ArrayList<String>();
        for (Map<String, Long> iterable : TipoProtocolo.tipoProtocolo) {
            for (String keyString : iterable.keySet()) {
                auxKeysProtocolos.add(keyString);
            }
        }
        return auxKeysProtocolos;
    }

    public Protocolo gerarProtocolo(Protocolo protocolo) {
        Long idCartorio = -1L;
        for (Map<String, Long> curr : TipoProtocolo.tipoProtocolo) {
            if (curr.containsKey(protocolo.getQualidadeProtocolo())) {
                idCartorio = curr.get(protocolo.getQualidadeProtocolo());
                break;
            }
        }
        Protocolo auxProtocolo = new Protocolo(protocolo, idCartorio);
        return this.repository.save(auxProtocolo);
    }

    public Protocolo getProtocoloByPessoa(String idPessoa) {
        Optional<Protocolo> protocolo = this.repository.findProtocoloByTitularProtocolo(idPessoa);
        if (!protocolo.isPresent())
            throw new IllegalStateException("Protocolo não encontrado!");
        return protocolo.get();
    }

    public Protocolo getProtocoloByQualidade(String qualidadeProtocolo) {
        Optional<Protocolo> protocolo = this.repository.findProtocoloByQualidadeProtocolo(qualidadeProtocolo);
        if (!protocolo.isPresent())
            throw new IllegalStateException("Protocolo não encontrado!");
        return protocolo.get();
    }

    public boolean deleteProtocolo(Long idProtocolo) {
        try {
            this.repository.deleteById(idProtocolo);
            Optional<Protocolo> exists = this.repository.findById(idProtocolo);
            return !exists.isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
