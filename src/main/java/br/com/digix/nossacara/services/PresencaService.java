package br.com.digix.nossacara.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.EntradaResponseDTO;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;

@Service
public class PresencaService {

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    public EntradaResponseDTO buscarComparecimento(LocalDate dia, LocalDeEntrada localDeEntrada, Escola escola) {
        int quantidadeEntrada = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(dia, localDeEntrada.getNumeroDispositivo());
        int quantidadeAusente = escola.getQuantidadeAlunos() - quantidadeEntrada;
        return EntradaResponseDTO.builder().quantidadeEntrada(quantidadeEntrada).quantidadeAusente(quantidadeAusente).build();
    }
}

