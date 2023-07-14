package br.com.digix.nossacara.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

import br.com.digix.nossacara.dtos.AlunoPresenteResponseDTO;
import br.com.digix.nossacara.models.*;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.mappers.ListagemDeAlunosMapper;
import br.com.digix.nossacara.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ReconhecimentoRepository reconhecimentoRepository;
    private final ListagemDeAlunosMapper mapper;

    public AlunoService(AlunoRepository alunoRepository, ReconhecimentoRepository reconhecimentoRepository, ListagemDeAlunosMapper mapper) {
        this.alunoRepository = alunoRepository;
        this.reconhecimentoRepository = reconhecimentoRepository;
        this.mapper = mapper;
    }

    public ListagemAlunosResponseDTO criarListaAlunosPresentes(LocalDate data, Escola escola, String nomeAluno, long etapaDeEnsinoId, int currentPage, int pageSize) {
        Page<Aluno> alunos = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, nomeAluno, etapaDeEnsinoId, data, PageRequest.of(currentPage, pageSize));
        ListagemAlunosResponseDTO alunosResponseDTO = mapper.from(alunos);
        alunosResponseDTO.getAlunos().forEach(aluno -> {
            aluno.setHorarioEntrada(getHorarioEntradaEscola(data, escola, aluno));
            aluno.setHorariosRefeitorio(getHorariosEntradaRefeitorio(data, escola, aluno));
        });
        var total = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(data, escola.getLocaisDeEntrada().stream().map(LocalDeEntrada::getNumeroDispositivo).toList(), nomeAluno, etapaDeEnsinoId);
        alunosResponseDTO.getPageInfo().setTotalPages(countNumberOfPages(total, pageSize));
        return alunosResponseDTO;
    }
    public ListagemAlunosResponseDTO criarListaAlunosPresentesNoRefeitorio(LocalDate data, Escola escola, String nomeAluno, long etapaDeEnsinoId, int currentPage, int pageSize) {
        Page<Aluno> alunos = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, nomeAluno, etapaDeEnsinoId, data, PageRequest.of(currentPage, pageSize));
        ListagemAlunosResponseDTO alunosResponseDTO = mapper.from(alunos);
        alunosResponseDTO.getAlunos().forEach(aluno -> {
            aluno.setHorariosRefeitorio(getHorariosEntradaRefeitorio(data, escola, aluno));
        });
        var total = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(data, escola.getLocaisDeEntrada().stream().map(LocalDeEntrada::getNumeroDispositivo).toList(), nomeAluno, etapaDeEnsinoId);
        alunosResponseDTO.getPageInfo().setTotalPages(countNumberOfPages(total, pageSize));
        return alunosResponseDTO;
    }

    private int countNumberOfPages(int numberOfObjects, int pageSize) {
        return numberOfObjects / pageSize + (numberOfObjects % pageSize == 0 ? 0 : 1);
    }

    private String getHorarioEntradaEscola(LocalDate data, Escola escola, AlunoPresenteResponseDTO aluno) {
        List<String> dispositivosDeEntradaEscolaId = escola.getLocaisDeEntrada().stream().map(LocalDeEntrada::getNumeroDispositivo).toList();
        List<Reconhecimento> reconhecimentosEntradaEscola = reconhecimentoRepository.findAllByDataDeCriacaoAndPersonIdAndDeviceKey(data, dispositivosDeEntradaEscolaId, aluno.getPersonId());
        return reconhecimentosEntradaEscola.stream().findFirst().get().getDataDeCriacao().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private List<String> getHorariosEntradaRefeitorio(LocalDate data, Escola escola, AlunoPresenteResponseDTO aluno) {
        List<String> dispositivosDeEntradaEscolaId = escola.getRefeitorios().stream().map(Refeitorio::getNumeroDispositivo).toList();
        List<Reconhecimento> reconhecimentosEntradaEscola = reconhecimentoRepository.findAllByDataDeCriacaoAndPersonIdAndDeviceKey(data, dispositivosDeEntradaEscolaId, aluno.getPersonId());
        return reconhecimentosEntradaEscola.stream().map(reconhecimento -> reconhecimento.getDataDeCriacao().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))).toList();
    }


}
