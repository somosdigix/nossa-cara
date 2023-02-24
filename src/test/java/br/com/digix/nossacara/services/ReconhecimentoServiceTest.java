package br.com.digix.nossacara.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import builders.ReconhecimentoRequestDTOBuilder;

@SpringBootTest
class ReconhecimentoServiceTest {

    @Autowired
    private ReconhecimentoService reconhecimentoService;

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @BeforeEach
    void setUp() {
        reconhecimentoRepository.deleteAll();
    }
    
    @Test 
    void deve_cadastrar_um_reconhecimento() throws Exception {
        // Arrange
        ReconhecimentoRequestDTO reconhecimentoRequestDTO = new ReconhecimentoRequestDTOBuilder().construir();

        // Action
        ReconhecimentoResponseDTO response = reconhecimentoService.cadastrar(reconhecimentoRequestDTO);

        // Assert
        assertThat(response.getId()).isNotNull();
    }
}
