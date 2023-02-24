package br.com.digix.nossacara.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;

import java.util.ArrayList;
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
