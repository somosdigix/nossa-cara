package br.com.digix.nossacara.services;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.mappers.EtapaDeEnsinoMapper;
import br.com.digix.nossacara.models.EtapaDeEnsino;
import br.com.digix.nossacara.repository.EtapaDeEnsinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtapaDeEnsinoService {

    private final EtapaDeEnsinoRepository etapaDeEnsinoRepository;
    private final EtapaDeEnsinoMapper etapaDeEnsinoMapper;

    public EtapaDeEnsinoResponseDTO buscarPorId(Long id) {
        return etapaDeEnsinoMapper.etapaDeEnsinoparaEtapaDeEnsinoResponse(etapaDeEnsinoRepository.findById(id).get());
    }

    public List<EtapaDeEnsinoResponseDTO> buscarTodos() {
        List<EtapaDeEnsino> etapasDeEnsino = etapaDeEnsinoRepository.findAll();
        return etapaDeEnsinoMapper.paraListaEtapaDeEnsino(etapasDeEnsino);
    }
}
