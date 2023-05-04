package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Refeitorio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RefeitorioRepository extends CrudRepository<Refeitorio, Long> {

    List<Refeitorio> findAll();
}