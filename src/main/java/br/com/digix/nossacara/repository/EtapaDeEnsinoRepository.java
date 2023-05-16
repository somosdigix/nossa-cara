package br.com.digix.nossacara.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digix.nossacara.models.EtapaDeEnsino;

@Repository
public interface EtapaDeEnsinoRepository extends JpaRepository<EtapaDeEnsino, Long> {
    
    Optional<EtapaDeEnsino> findByNome(String nome);
}
