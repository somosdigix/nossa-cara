package br.com.digix.nossacara.mappers;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.models.EtapaDeEnsino;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EtapaDeEnsinoMapperImpl implements EtapaDeEnsinoMapper {

    @Override
    public EtapaDeEnsinoResponseDTO etapaDeEnsinoparaEtapaDeEnsinoResponse(EtapaDeEnsino etapaDeEnsino) {
        return getEtapaDeEnsinoResponseDTO(etapaDeEnsino);
    }

    private static EtapaDeEnsinoResponseDTO getEtapaDeEnsinoResponseDTO(EtapaDeEnsino etapaDeEnsino) {
        return EtapaDeEnsinoResponseDTO
                .builder()
                .id(etapaDeEnsino.getId())
                .nome(etapaDeEnsino.getNome())
                .build();
    }

    @Override
    public List<EtapaDeEnsinoResponseDTO> paraListaEtapaDeEnsino(List<EtapaDeEnsino> etapasDeEnsino) {
        return etapasDeEnsino.stream()
                .map(EtapaDeEnsinoMapperImpl::getEtapaDeEnsinoResponseDTO)
                .toList();
    }
}
