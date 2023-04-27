package br.com.digix.nossacara.services;

import java.time.LocalDate;

import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.mappers.ListagemDeAlunosMapper;
import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.repository.AlunoRepository;
import br.com.digix.nossacara.repository.LocalDeEntradaRepository;
import br.com.digix.nossacara.repository.RefeitorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.repository.ReconhecimentoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ListagemDeAlunosMapper mapper;

    public AlunoService(AlunoRepository alunoRepository, ListagemDeAlunosMapper mapper) {
        this.alunoRepository = alunoRepository;
        this.mapper = mapper;
    }

    public ListagemAlunosResponseDTO criarListaAlunosPresentes(LocalDate data, Escola escola, int currentPage, int pageSize) {
        Page<Aluno> alunos = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, data, PageRequest.of(currentPage, pageSize));
        return mapper.from(alunos);
    }
}
