package br.com.digix.nossacara.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.digix.nossacara.models.EtapaDeEnsino;


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

    @Test
    public void deve_buscar_uma_escola_pelo_nome() {
        // Arrange
        String nomeEsperado = "Ensino Médio";
        EtapaDeEnsino etapaDeEnsinoEsperado = EtapaDeEnsino.builder().nome(nomeEsperado).build();

        // Action
        etapaDeEnsinoRepository.save(etapaDeEnsinoEsperado);
        List<EtapaDeEnsino> etapasDeEnsinoRetornadas = etapaDeEnsinoRepository.findByNomeContaining(nomeEsperado);

        // Asserts
        assertTrue(etapasDeEnsinoRetornadas.contains( etapaDeEnsinoEsperado));

    }
}
