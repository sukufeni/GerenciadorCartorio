package com.passGenerator.PassGenarator.protocolo;

import com.passGenerator.PassGenarator.Cartorio.Cartorio;
import com.passGenerator.PassGenarator.Cartorio.CartorioRepository;
import com.passGenerator.PassGenarator.Pessoa.Pessoa;
import com.passGenerator.PassGenarator.Pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
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

    @Autowired
    private final CartorioRepository cartorioRepository;

    public ProtocoloService(ProtocoloRepository repository, PessoaRepository pessoaRepository,
            CartorioRepository cartorioRepository) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
        this.cartorioRepository = cartorioRepository;
    }

    public List<Protocolo> getProtocolos() {
        return this.repository.findProtocolosActive();
    }

    public Object imprimirProtocolos(String idCartorio) {
        return this.repository.findByDataCriacao(LocalDate.now()).get().stream()
                .filter(o -> o.getCartorio() == Long.parseLong(idCartorio)).toArray();
    }

    public ByteArrayInputStream imprimirProtocoloDetalhado(String idProtocolo) {

        Protocolo returnedProtocolo = this.repository.getById(Long.parseLong(idProtocolo));
        Pessoa apresentante = pessoaRepository.getById(returnedProtocolo.getTitularProtocolo());
        Cartorio emitente = cartorioRepository.getById(returnedProtocolo.getCartorio());

        return PdfGenerator.generateProtocolo(returnedProtocolo, apresentante.getNome(), emitente);
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

    public boolean disableProtocolo(Long idProtocolo, String observacao) {
        try {
            Optional<Protocolo> auxProtocolo = this.repository.findById(idProtocolo);
            if (auxProtocolo.isPresent()) {
                Protocolo found = auxProtocolo.get();
                found.disableProtocolo(observacao);
                this.repository.save(found);
                return found.getExcluido();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
