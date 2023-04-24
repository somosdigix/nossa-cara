package br.com.digix.nossacara.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.com.digix.nossacara.models.LocalDeEntrada;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalDeEntradaRepository extends CrudRepository<LocalDeEntrada, Long> {

    List<LocalDeEntrada> findAll();

}
