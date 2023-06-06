package br.com.digix.nossacara.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.mappers.ListagemDeAlunosMapper;
import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.EtapaDeEnsino;
import br.com.digix.nossacara.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ListagemDeAlunosMapper mapper;

    public AlunoService(AlunoRepository alunoRepository, ListagemDeAlunosMapper mapper) {
        this.alunoRepository = alunoRepository;
        this.mapper = mapper;
    }

    public ListagemAlunosResponseDTO criarListaAlunosPresentes(LocalDate data, Escola escola,EtapaDeEnsino etapaDeEnsino, int currentPage, int pageSize) {
        Page<Aluno> alunos = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, data,etapaDeEnsino, PageRequest.of(currentPage, pageSize));
        return mapper.from(alunos);
    }

   
}
