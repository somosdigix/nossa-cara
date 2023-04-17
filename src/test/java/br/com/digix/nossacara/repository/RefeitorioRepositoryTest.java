package br.com.digix.nossacara.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.digix.nossacara.models.Refeitorio;

@DataJpaTest
class RefeitorioRepositoryTest {

    @Autowired
    private RefeitorioRepository refeitorioRepository;

    @BeforeEach
    void setUp() {
        refeitorioRepository.deleteAll();
    }
    
    @Test
    void deve_salvar_um_refeitorio() {
        // Arrange
        String numeroDispositivo = "84E0F4210D4C607A";
        String nome = "Refeitorio Central";
        Refeitorio refeitorioCentral =
                Refeitorio
                        .builder()
                        .numeroDispositivo(numeroDispositivo)
                        .nome(nome)
                        .build();

        // Action
        refeitorioRepository.save(refeitorioCentral);

        // Asserts
        assertThat(refeitorioCentral.getId()).isNotNull();
    }
}

