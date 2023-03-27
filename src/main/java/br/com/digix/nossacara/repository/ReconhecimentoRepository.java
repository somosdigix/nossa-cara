package br.com.digix.nossacara.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.digix.nossacara.models.Reconhecimento;

public interface ReconhecimentoRepository extends CrudRepository<Reconhecimento, Long> {

    public List<Reconhecimento> findAll();
    
}
