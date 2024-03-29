package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Reconhecimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReconhecimentoRepository extends CrudRepository<Reconhecimento, Long>, CustomReconhecimentoRepository {

    List<Reconhecimento> findAll();

    Reconhecimento findFirstByPersonIdOrderByIdDesc(String personId);

}