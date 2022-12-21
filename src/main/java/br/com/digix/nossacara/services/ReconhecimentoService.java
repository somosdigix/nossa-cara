package br.com.digix.nossacara.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;

@Service
public class ReconhecimentoService {

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    public ReconhecimentoResponseDTO cadastrar(ReconhecimentoRequestDTO reconhecimentoRequestDTO) {
        Long personId = Long.parseLong(reconhecimentoRequestDTO.getPersonId());
        Long time = Long.parseLong(reconhecimentoRequestDTO.getPersonId());
        Reconhecimento reconhecimento = new Reconhecimento(reconhecimentoRequestDTO.getDeviceKey(), 
                                                        personId, time, 
                                                        reconhecimentoRequestDTO.getIp(), 
                                                        reconhecimentoRequestDTO.getType(), 
                                                        reconhecimentoRequestDTO.getPath());
        reconhecimentoRepository.save(reconhecimento);
        return new ReconhecimentoResponseDTO(reconhecimento.getId(), reconhecimento.getDeviceKey(), reconhecimento.getPersonId(), reconhecimento.getTime(), reconhecimento.getIp(), reconhecimento.getType(), reconhecimento.getPath());
    }
    
}
