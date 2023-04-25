package br.com.digix.nossacara.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.com.digix.nossacara.models.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long>, CustomAlunoRepository {

    List<Aluno> findByNomeContaining(String nome);

    public List<Aluno> findAll();
}
