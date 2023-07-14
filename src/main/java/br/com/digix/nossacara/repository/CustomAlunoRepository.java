package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface CustomAlunoRepository{

    Page<Aluno> buscarAlunosComReconhecimentoNoDia(Escola escola, String nomeAluno, long etapaDeEnsinoId, LocalDate dia, Pageable pageable);
    Page<Aluno> buscarAlunosAusentesNaEntrada(Escola escola, String nomeAluno, long etapaDeEnsinoId, LocalDate dia, Pageable pageable);
    Page<Aluno> buscarAlunosComReconhecimentoNoRefeitorio (Escola escola, String nomeAluno, long etapaDeEnsinoId, LocalDate dia, Pageable pageable);
    Page<Aluno> buscarAlunosAusentesNoRefeitorio (Escola escola, String nomeAluno, long etapaDeEnsinoId, LocalDate dia, Pageable pageable);
}
