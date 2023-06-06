package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface CustomAlunoRepository{

    Page<Aluno> buscarAlunosComReconhecimentoNoDia(Escola escola, String nomeAluno, LocalDate dia, Pageable pageable);
}
