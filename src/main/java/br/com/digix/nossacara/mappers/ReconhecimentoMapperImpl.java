package br.com.digix.nossacara.mappers;

import org.springframework.stereotype.Component;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.models.Reconhecimento;

@Component
public class ReconhecimentoMapperImpl implements ReconhecimentoMapper {

    @Override
    public Reconhecimento reconhecimentoRequestParaReconhecimento(ReconhecimentoRequestDTO reconhecimentoRequestDTO) {
        Long personId = Long.parseLong(reconhecimentoRequestDTO.getPersonId());
        Long time = Long.parseLong(reconhecimentoRequestDTO.getTime());

        return new Reconhecimento(reconhecimentoRequestDTO.getDeviceKey(), personId, time,
                reconhecimentoRequestDTO.getIp(), reconhecimentoRequestDTO.getType(),
                reconhecimentoRequestDTO.getPath());
    }

    @Override
    public ReconhecimentoResponseDTO reconhecimentoParaReconhecimentoResponse(Reconhecimento reconhecimento) {
        
        return new ReconhecimentoResponseDTO(reconhecimento.getId(), reconhecimento.getDeviceKey(), reconhecimento.getPersonId(), reconhecimento.getTime(), reconhecimento.getIp(), reconhecimento.getType(), reconhecimento.getPath());
    }

}
