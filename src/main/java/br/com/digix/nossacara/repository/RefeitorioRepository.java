package br.com.digix.nossacara.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.digix.nossacara.models.Refeitorio;

public interface RefeitorioRepository extends CrudRepository<Refeitorio, Long> {

    public List<Refeitorio> findAll();

}
