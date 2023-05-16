package br.com.digix.nossacara.services;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.mappers.EtapaDeEnsinoMapper;
import br.com.digix.nossacara.models.EtapaDeEnsino;
import br.com.digix.nossacara.repository.EtapaDeEnsinoRepository;

@Service
public class EtapaDeEnsinoService {
    
    private EtapaDeEnsinoRepository etapaDeEnsinoRepository;

    private EtapaDeEnsinoMapper etapaDeEnsinoMapper;

    public EtapaDeEnsinoService(EtapaDeEnsinoRepository etapaDeEnsinoRepository) {
        this.etapaDeEnsinoRepository = etapaDeEnsinoRepository;
    }
    
    public EtapaDeEnsino salvar(EtapaDeEnsino etapaDeEnsino) {
        return etapaDeEnsinoRepository.save(etapaDeEnsino);
    }
    public EtapaDeEnsinoResponseDTO buscarPorId(Long id)  {
        return etapaDeEnsinoMapper.etapaDeEnsinoResponseparaEtapaDeEnsino(buscarPorId(id));
    }

    
    
}
