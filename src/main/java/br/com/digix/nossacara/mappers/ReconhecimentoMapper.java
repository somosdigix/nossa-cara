package br.com.digix.nossacara.mappers;
import br.com.digix.nossacara.dtos.ReconhecimentoSucessResponseDTO;
import org.mapstruct.Mapper;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.models.Reconhecimento;

@Mapper(componentModel = "spring")
public interface ReconhecimentoMapper {
    Reconhecimento reconhecimentoRequestParaReconhecimento(ReconhecimentoRequestDTO reconhecimentoRequestDTO);

    ReconhecimentoResponseDTO reconhecimentoParaReconhecimentoResponse(Reconhecimento reconhecimento);
    ReconhecimentoSucessResponseDTO reconhecimentoParaReconhecimentoSucessResponse();
}
