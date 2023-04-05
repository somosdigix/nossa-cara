package br.com.digix.nossacara.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.com.digix.nossacara.models.LocalDeEntrada;

public interface LocalDeEntradaRepository extends CrudRepository<LocalDeEntrada, Long> {

    public List<LocalDeEntrada> findAll();

}
