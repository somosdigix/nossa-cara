package br.com.digix.nossacara.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.digix.nossacara.models.Reconhecimento;

@Repository
public interface ReconhecimentoRepository extends CrudRepository<Reconhecimento, Long> {
    
}
