package br.com.digix.nossacara.mappers;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.models.EtapaDeEnsino;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EtapaDeEnsinoMapper {
    EtapaDeEnsinoResponseDTO etapaDeEnsinoparaEtapaDeEnsinoResponse(EtapaDeEnsino etapaDeEnsino);

    List<EtapaDeEnsinoResponseDTO> paraListaEtapaDeEnsino(List<EtapaDeEnsino> etapasDeEnsino);
}
