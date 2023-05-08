package br.com.digix.nossacara.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.digix.nossacara.models.Reconhecimento;

@Repository
public interface ReconhecimentoRepository extends CrudRepository<Reconhecimento, Long>, CustomReconhecimentoRepository {

    List<Reconhecimento> findAll();

    Reconhecimento findFirstByPersonIdOrderByIdDesc(String personId);

}