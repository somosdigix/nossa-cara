package br.com.digix.nossacara.services;

import br.com.digix.nossacara.dtos.EntradaResponseDTO;
import br.com.digix.nossacara.dtos.PresencaResponseDTO;
import br.com.digix.nossacara.dtos.RefeitorioResponseDTO;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Refeitorio;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PresencaService {
    private final ReconhecimentoRepository reconhecimentoRepository;

    public PresencaService(ReconhecimentoRepository reconhecimentoRepository) {
        this.reconhecimentoRepository = reconhecimentoRepository;
    }

    public EntradaResponseDTO buscarComparecimento(LocalDate dia, LocalDeEntrada localDeEntrada, Escola escola) {
        return calcularEntradaEscola(dia, localDeEntrada, escola);
    }

    private EntradaResponseDTO calcularEntradaEscola(LocalDate dia, LocalDeEntrada localDeEntrada, Escola escola) {
        int quantidadeEntrada = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(dia, localDeEntrada.getNumeroDispositivo());
        int quantidadeAusente = escola.getQuantidadeAlunos() - quantidadeEntrada;
        return EntradaResponseDTO.builder().quantidadeEntrada(quantidadeEntrada).quantidadeAusente(quantidadeAusente).build();
    }

    public RefeitorioResponseDTO buscarComparecimento(LocalDate dia, Refeitorio refeitorio, LocalDeEntrada localDeEntrada) {
        return calcularEntradaRefeitorio(dia, refeitorio, localDeEntrada);
    }

    private RefeitorioResponseDTO calcularEntradaRefeitorio(LocalDate dia, Refeitorio refeitorio, LocalDeEntrada localDeEntrada) {
        int quantidadeRefeitorio = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(dia, refeitorio.getNumeroDispositivo());
        int quantidadeAusente = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(dia, localDeEntrada.getNumeroDispositivo()) - quantidadeRefeitorio;
        return RefeitorioResponseDTO.builder().quantidadeEntrada(quantidadeRefeitorio).quantidadeAusente(quantidadeAusente).build();
    }

    public PresencaResponseDTO buscarComparecimento(LocalDate dia, Refeitorio refeitorio, LocalDeEntrada localDeEntrada, Escola escola) {
        EntradaResponseDTO entradaResponseDTO = calcularEntradaEscola(dia, localDeEntrada, escola);
        RefeitorioResponseDTO refeitorioResponseDTO = calcularEntradaRefeitorio(dia, refeitorio, localDeEntrada);
        return PresencaResponseDTO.builder()
                .quantidadeEntradaEscola(entradaResponseDTO.getQuantidadeEntrada())
                .quantidadeAusenteEscola(entradaResponseDTO.getQuantidadeAusente())
                .quantidadeEntradaRefeitorio(refeitorioResponseDTO.getQuantidadeEntrada())
                .quantidadeAusenteRefeitorio(refeitorioResponseDTO.getQuantidadeAusente())
                .build();
    }
}