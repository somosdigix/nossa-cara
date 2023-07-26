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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresencaService {
    private final ReconhecimentoRepository reconhecimentoRepository;

    public PresencaService(ReconhecimentoRepository reconhecimentoRepository) {
        this.reconhecimentoRepository = reconhecimentoRepository;
    }

    public EntradaResponseDTO buscarComparecimentoEntrada(LocalDate dia, Escola escola) {
        return calcularEntradaEscola(dia, escola);
    }

    private EntradaResponseDTO calcularEntradaEscola(LocalDate dia, Escola escola) {
        int quantidadeEntrada = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(dia, getTodosNumerosDispositivosEntrada(escola));
        int quantidadeAusente = escola.getQuantidadeAlunos() - quantidadeEntrada;
        return EntradaResponseDTO.builder().quantidadeEntrada(quantidadeEntrada).quantidadeAusente(quantidadeAusente).build();
    }

    static List<String> getNumerosDispositivosEntrada(Escola escola) {
        return escola.getLocaisDeEntrada().stream().map(LocalDeEntrada::getNumeroDispositivo).collect(Collectors.toList());
    }

    static List<String> getTodosNumerosDispositivosEntrada(Escola escola) {
        List<String> listaDispositivosEntrada = getNumerosDispositivosEntrada(escola);
        listaDispositivosEntrada.addAll(getNumerosDispositivosRefeitorio(escola));
        return listaDispositivosEntrada;
    }

    static List<String> getNumerosDispositivosRefeitorio(Escola escola) {
        return escola.getRefeitorios().stream().map(Refeitorio::getNumeroDispositivo).toList();
    }

    public RefeitorioResponseDTO buscarComparecimentoRefeitorio(LocalDate dia, Escola escola) {
        return calcularEntradaRefeitorio(dia, escola);
    }

    private RefeitorioResponseDTO calcularEntradaRefeitorio(LocalDate dia, Escola escola) {
        int quantidadeRefeitorio = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(dia, getNumerosDispositivosRefeitorio(escola));
        int quantidadeAusente = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(dia, getNumerosDispositivosEntrada(escola)) - quantidadeRefeitorio;
        return RefeitorioResponseDTO.builder().quantidadeEntrada(quantidadeRefeitorio).quantidadeAusente(quantidadeAusente).build();
    }

    public PresencaResponseDTO buscarComparecimento(LocalDate dia, Escola escola) {
        EntradaResponseDTO entradaResponseDTO = calcularEntradaEscola(dia, escola);
        RefeitorioResponseDTO refeitorioResponseDTO = calcularEntradaRefeitorio(dia, escola);
        return PresencaResponseDTO.builder()
                .quantidadeEntradaEscola(entradaResponseDTO.getQuantidadeEntrada())
                .quantidadeAusenteEscola(entradaResponseDTO.getQuantidadeAusente())
                .quantidadeEntradaRefeitorio(refeitorioResponseDTO.getQuantidadeEntrada())
                .quantidadeAusenteRefeitorio(refeitorioResponseDTO.getQuantidadeAusente())
                .build();
    }
}