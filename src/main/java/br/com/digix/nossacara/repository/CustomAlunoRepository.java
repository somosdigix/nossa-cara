package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CustomAlunoRepository{

    List<Aluno> buscarAlunosComReconhecimentoNoDia(Escola escola, LocalDate dia, Pageable pageable);
}
