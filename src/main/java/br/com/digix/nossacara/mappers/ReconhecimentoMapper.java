package br.com.digix.nossacara.mappers;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoSucessResponseDTO;
import br.com.digix.nossacara.models.Reconhecimento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReconhecimentoMapper {
    Reconhecimento reconhecimentoRequestParaReconhecimento(ReconhecimentoRequestDTO reconhecimentoRequestDTO);

    ReconhecimentoResponseDTO reconhecimentoParaReconhecimentoResponse(Reconhecimento reconhecimento);

    ReconhecimentoSucessResponseDTO reconhecimentoParaReconhecimentoSucessResponse();
}