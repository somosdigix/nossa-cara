package br.com.digix.nossacara.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.digix.nossacara.builder.ReconhecimentoBuilder;
import br.com.digix.nossacara.models.Reconhecimento;

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
