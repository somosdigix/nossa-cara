package br.com.digix.nossacara.mappers;

import org.mapstruct.Mapper;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoRequestDTO;
import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.models.EtapaDeEnsino;

@Mapper(componentModel = "spring")
public interface EtapaDeEnsinoMapper {
    public EtapaDeEnsino estapaDeEnsinoRequestParaEtapaDeEnsino(EtapaDeEnsinoRequestDTO etapaDeEnsinoRequestDTO);
    public EtapaDeEnsinoResponseDTO etapaDeEnsinoResponseparaEtapaDeEnsino(EtapaDeEnsinoResponseDTO etapaDeEnsinoResponseDTO);
}
