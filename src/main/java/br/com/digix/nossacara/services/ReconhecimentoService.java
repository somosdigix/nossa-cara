package br.com.digix.nossacara.services;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.mappers.ReconhecimentoMapper;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReconhecimentoService {

    private final ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ReconhecimentoService(ReconhecimentoRepository reconhecimentoRepository) {
        this.reconhecimentoRepository = reconhecimentoRepository;
    }

    @Autowired
    private ReconhecimentoMapper reconhecimentoMapper;

    public ReconhecimentoResponseDTO cadastrar(ReconhecimentoRequestDTO reconhecimentoRequestDTO) {
        Reconhecimento reconhecimento = reconhecimentoMapper
                .reconhecimentoRequestParaReconhecimento(reconhecimentoRequestDTO);
        reconhecimentoRepository.save(reconhecimento);
        return reconhecimentoMapper.reconhecimentoParaReconhecimentoResponse(reconhecimento);
    }

    public List<ReconhecimentoResponseDTO> buscarTodos() {
        List<Reconhecimento> todos = reconhecimentoRepository.findAll();
        return todos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReconhecimentoResponseDTO convertToDto(Reconhecimento reconhecimento) {
        return modelMapper.map(reconhecimento, ReconhecimentoResponseDTO.class);
    }
}
