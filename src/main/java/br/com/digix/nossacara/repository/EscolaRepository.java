package br.com.digix.nossacara.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.digix.nossacara.models.Escola;

@Repository
public interface EscolaRepository extends CrudRepository<Escola, Long>  {
    List<Escola> findByNomeContaining(String nome);

    public List<Escola> findAll();
}
