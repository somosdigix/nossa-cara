package br.com.digix.nossacara.repository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.digix.nossacara.models.EtapaDeEnsino;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class EtapaDeEnsinoRepositoryTest {
    @Autowired
    private EtapaDeEnsinoRepository etapaDeEnsinoRepository;

    @Test
    void deve_salvar_uma_etapaDeEnsino(){
         // Arrange
         EtapaDeEnsino etapaDeEnsino = EtapaDeEnsino.builder().nome("Ensino Médio").build();

         // Action
         etapaDeEnsinoRepository.save(etapaDeEnsino);
 
         // Asserts
         assertThat(etapaDeEnsino.getId()).isNotNull();
    }
}
