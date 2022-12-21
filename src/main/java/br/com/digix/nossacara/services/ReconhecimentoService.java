package br.com.digix.nossacara.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.mappers.ReconhecimentoMapper;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;

@Service
public class ReconhecimentoService {

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private ReconhecimentoMapper reconhecimentoMapper;

    public ReconhecimentoResponseDTO cadastrar(ReconhecimentoRequestDTO reconhecimentoRequestDTO) {
        Reconhecimento reconhecimento = reconhecimentoMapper
                .reconhecimentoRequestParaReconhecimento(reconhecimentoRequestDTO);
        reconhecimentoRepository.save(reconhecimento);
        return reconhecimentoMapper.reconhecimentoParaReconhecimentoResponse(reconhecimento);
    }
}
