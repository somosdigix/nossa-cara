package br.com.digix.nossacara.services;

import br.com.digix.nossacara.dtos.AlunoPresenteResponseDTO;
import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.mappers.ListagemDeAlunosMapper;
import br.com.digix.nossacara.models.*;
import br.com.digix.nossacara.repository.AlunoRepository;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AlunoService {

    private static final String ENTRADA_NAO_DETECTADA = "Entrada não detectada";
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
        List<String> todosDispositivos = new ArrayList<>();
        todosDispositivos.addAll(escola.getLocaisDeEntrada().stream()
                .map(LocalDeEntrada::getNumeroDispositivo)
                .toList());

        todosDispositivos.addAll(escola.getRefeitorios().stream()
                .map(Refeitorio::getNumeroDispositivo)
                .toList());
        var total = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(data, todosDispositivos, nomeAluno, etapaDeEnsinoId);
        alunosResponseDTO.getPageInfo().setTotalPages(countNumberOfPages(total, pageSize));
        return alunosResponseDTO;
    }

    public ListagemAlunosResponseDTO criarListaAlunosPresentesNoRefeitorio(LocalDate data, Escola escola, String nomeAluno, long etapaDeEnsinoId, int currentPage, int pageSize) {
        Page<Aluno> alunos = alunoRepository.buscarAlunosComReconhecimentoNoDiaNoRefeitorio(escola, nomeAluno, etapaDeEnsinoId, data, PageRequest.of(currentPage, pageSize));
        ListagemAlunosResponseDTO alunosResponseDTO = mapper.from(alunos);
        alunosResponseDTO.getAlunos().forEach(aluno -> {
            aluno.setHorarioEntrada(getHorarioEntradaEscola(data, escola, aluno));
            aluno.setHorariosRefeitorio(getHorariosEntradaRefeitorio(data, escola, aluno));
        });
        var total = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(data, escola.getRefeitorios().stream().map(Refeitorio::getNumeroDispositivo).toList(), nomeAluno, etapaDeEnsinoId);
        alunosResponseDTO.getPageInfo().setTotalPages(countNumberOfPages(total, pageSize));
        return alunosResponseDTO;
    }

    public ListagemAlunosResponseDTO criarListaAlunosAusentesNaEntrada(LocalDate data, Escola escola, String nomeAluno, long etapaDeEnsinoId, int currentPage, int pageSize) {
        Page<Aluno> alunos = alunoRepository.buscarAlunosAusentesNaEntrada(escola, nomeAluno, etapaDeEnsinoId, data, PageRequest.of(currentPage, pageSize));
        ListagemAlunosResponseDTO alunosResponseDTO = mapper.from(alunos);
        alunosResponseDTO.getAlunos().forEach(aluno -> {
            aluno.setHorarioEntrada(getHorarioEntradaEscola(data, escola, aluno));
            aluno.setHorariosRefeitorio(getHorariosEntradaRefeitorio(data, escola, aluno));
        });
        List<String> todosDispositivos = new ArrayList<>();
        todosDispositivos.addAll(escola.getLocaisDeEntrada().stream()
                .map(LocalDeEntrada::getNumeroDispositivo)
                .toList());

        todosDispositivos.addAll(escola.getRefeitorios().stream()
                .map(Refeitorio::getNumeroDispositivo)
                .toList());


        var total = reconhecimentoRepository.quantidadeDeAusenciasDistintas(data, todosDispositivos, nomeAluno, etapaDeEnsinoId);
        alunosResponseDTO.getPageInfo().setTotalPages(countNumberOfPages(total, pageSize));
        return alunosResponseDTO;
    }

    public ListagemAlunosResponseDTO criarListaAlunosAusentesNoRefeitorio(LocalDate data, Escola escola, String nomeAluno, long etapaDeEnsinoId, int currentPage, int pageSize) {
        Page<Aluno> alunos = alunoRepository.buscarAlunosAusentesNoRefeitorio(escola, nomeAluno, etapaDeEnsinoId, data, PageRequest.of(currentPage, pageSize));
        ListagemAlunosResponseDTO alunosResponseDTO = mapper.from(alunos);
        alunosResponseDTO.getAlunos().forEach(aluno -> {
            aluno.setHorarioEntrada(getHorarioEntradaEscola(data, escola, aluno));
            aluno.setHorariosRefeitorio(getHorariosEntradaRefeitorio(data, escola, aluno));
        });

        List<String> dispositivosRefeitorio = escola.getRefeitorios().stream()
                .map(Refeitorio::getNumeroDispositivo)
                .toList();
        List<String> dispositivosEscola = escola.getLocaisDeEntrada().stream()
                .map(LocalDeEntrada::getNumeroDispositivo)
                .toList();

        int alunosPresentesNaEscola = reconhecimentoRepository.quantidadeDeAlunosPresentesNaEscola(data, dispositivosEscola);
        int alunosPresentesNoRefeitorio = reconhecimentoRepository.quantidadeDeAlunosPresentesNoRefeitorio(data, dispositivosRefeitorio);
        int totalAusentesRefeitorioReal = alunosPresentesNaEscola - alunosPresentesNoRefeitorio;

        alunosResponseDTO.getPageInfo().setTotalPages(countNumberOfPages(totalAusentesRefeitorioReal, pageSize));
        return alunosResponseDTO;
    }


    private int countNumberOfPages(int numberOfObjects, int pageSize) {
        return numberOfObjects / pageSize + (numberOfObjects % pageSize == 0 ? 0 : 1);
    }

    private String getHorarioEntradaEscola(LocalDate data, Escola escola, AlunoPresenteResponseDTO aluno) {
        List<String> dispositivosDeEntradaEscolaId = escola.getLocaisDeEntrada().stream().map(LocalDeEntrada::getNumeroDispositivo).toList();
        List<Reconhecimento> reconhecimentosEntradaEscola = reconhecimentoRepository.findAllByDataDeCriacaoAndPersonIdAndDeviceKey(data, dispositivosDeEntradaEscolaId, aluno.getPersonId());
        return reconhecimentosEntradaEscola.stream()
                .findFirst()
                .map(reconhecimento -> reconhecimento.getDataDeCriacao().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .orElse(ENTRADA_NAO_DETECTADA);
    }

    private List<String> getHorariosEntradaRefeitorio(LocalDate data, Escola escola, AlunoPresenteResponseDTO aluno) {
        List<String> dispositivosDeEntradaEscolaId = escola.getRefeitorios().stream().map(Refeitorio::getNumeroDispositivo).toList();
        List<Reconhecimento> reconhecimentosEntradaRefeitorio = reconhecimentoRepository.findAllByDataDeCriacaoAndPersonIdAndDeviceKey(data, dispositivosDeEntradaEscolaId, aluno.getPersonId());
        return Collections.singletonList(reconhecimentosEntradaRefeitorio.stream()
                .findFirst()
                .map(reconhecimento -> reconhecimento.getDataDeCriacao().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .orElse(ENTRADA_NAO_DETECTADA));
    }

}
