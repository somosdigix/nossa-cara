package br.com.digix.nossacara.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.digix.nossacara.models.Reconhecimento;

import java.util.List;

@Repository
public interface ReconhecimentoRepository extends CrudRepository<Reconhecimento, Long> {

    public List<Reconhecimento> findAll();
    
}

