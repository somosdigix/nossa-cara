package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Aluno;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlunoRepository extends CrudRepository<Aluno, Long>, CustomAlunoRepository {

    List<Aluno> findByNomeContaining(String nome);

    List<Aluno> findAll();
}