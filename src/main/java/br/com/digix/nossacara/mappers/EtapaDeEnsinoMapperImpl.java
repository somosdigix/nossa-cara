package br.com.digix.nossacara.mappers;

import org.springframework.stereotype.Component;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoRequestDTO;
import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.models.EtapaDeEnsino;

@Component
public class EtapaDeEnsinoMapperImpl implements EtapaDeEnsinoMapper {

    @Override
    public EtapaDeEnsino estapaDeEnsinoRequestParaEtapaDeEnsino(EtapaDeEnsinoRequestDTO etapaDeEnsinoRequestDTO) {
        return new EtapaDeEnsino(etapaDeEnsinoRequestDTO.getNome());
    }

    @Override
    public EtapaDeEnsinoResponseDTO etapaDeEnsinoResponseparaEtapaDeEnsino(EtapaDeEnsinoResponseDTO etapaDeEnsino) {
        return new EtapaDeEnsinoResponseDTO(etapaDeEnsino.getId(), etapaDeEnsino.getNome());
    }
}
