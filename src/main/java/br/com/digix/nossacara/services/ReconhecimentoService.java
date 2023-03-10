package br.com.digix.nossacara.services;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoSucessResponseDTO;
import br.com.digix.nossacara.mappers.ReconhecimentoMapper;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReconhecimentoService {

    private final ReconhecimentoRepository reconhecimentoRepository;

    public ReconhecimentoService(ReconhecimentoRepository reconhecimentoRepository, ReconhecimentoMapper reconhecimentoMapper) {
        this.reconhecimentoRepository = reconhecimentoRepository;
        this.reconhecimentoMapper = reconhecimentoMapper;
    }

    private final ReconhecimentoMapper reconhecimentoMapper;

    public ReconhecimentoSucessResponseDTO cadastrar(ReconhecimentoRequestDTO reconhecimentoRequestDTO) {
        Reconhecimento reconhecimento = reconhecimentoMapper
                .reconhecimentoRequestParaReconhecimento(reconhecimentoRequestDTO);
        reconhecimentoRepository.save(reconhecimento);
        return reconhecimentoMapper.reconhecimentoParaReconhecimentoSucessResponse();
    }

    public List<ReconhecimentoResponseDTO> buscarTodos() {
        List<Reconhecimento> todos = reconhecimentoRepository.findAll();
        return todos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReconhecimentoResponseDTO convertToDto(Reconhecimento reconhecimento) {
        return reconhecimentoMapper.reconhecimentoParaReconhecimentoResponse(reconhecimento);
    }
}
