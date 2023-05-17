package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Aluno;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends CrudRepository<Aluno, Long>, CustomAlunoRepository {

    Optional<Aluno> findFirstByPersonId(String personId);
    List<Aluno> findAll();
}