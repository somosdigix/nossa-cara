package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Reconhecimento;
import builders.ReconhecimentoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReconhecimentoRepositoryTest {

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @BeforeEach
    void setUp() {
        reconhecimentoRepository.deleteAll();
    }
    
    @Test
    void deve_salvar_um_reconhecimento() {
        // Arrange
        Reconhecimento reconhecimento = new ReconhecimentoBuilder().construir();

        // Action
        reconhecimentoRepository.save(reconhecimento);

        // Asserts
        assertThat(reconhecimento.getId()).isNotNull();
    }
}
