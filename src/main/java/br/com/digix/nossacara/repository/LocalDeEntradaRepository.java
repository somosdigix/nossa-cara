package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.LocalDeEntrada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalDeEntradaRepository extends CrudRepository<LocalDeEntrada, Long> {

    List<LocalDeEntrada> findAll();

}