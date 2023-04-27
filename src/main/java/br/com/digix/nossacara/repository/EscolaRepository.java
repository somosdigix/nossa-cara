package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Escola;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscolaRepository extends CrudRepository<Escola, Long> {
    List<Escola> findByNomeContaining(String nome);

    public List<Escola> findAll();
}