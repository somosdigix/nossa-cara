package br.com.digix.nossacara.mappers;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.models.Reconhecimento;
import org.springframework.stereotype.Component;

@Component
public class ReconhecimentoMapperImpl implements ReconhecimentoMapper {

    @Override
    public Reconhecimento reconhecimentoRequestParaReconhecimento(ReconhecimentoRequestDTO reconhecimentoRequestDTO) {
        return new Reconhecimento(reconhecimentoRequestDTO.getDeviceKey(), reconhecimentoRequestDTO.getPersonId(), DataConverter.toDate(reconhecimentoRequestDTO.getTime()),
                reconhecimentoRequestDTO.getIp(), reconhecimentoRequestDTO.getType(),
                reconhecimentoRequestDTO.getPath());
    }

    @Override
    public ReconhecimentoResponseDTO reconhecimentoParaReconhecimentoResponse(Reconhecimento reconhecimento) {
        
        return new ReconhecimentoResponseDTO(reconhecimento.getId(), reconhecimento.getDeviceKey(), reconhecimento.getPersonId().toString(), reconhecimento.getDataDeCriacao(), reconhecimento.getIp(), reconhecimento.getType(), reconhecimento.getPath());
    }

}
