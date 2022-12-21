package br.com.digix.nossacara.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;

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
    void deve_cadastrar_um_reconhecimento() {
        // Arrange
        String deviceKey = "84E0F42";
        String personId = "999";
        String time = "1651145957787";
        String ip = "192.168.11.2";
        String type = "face_0";
        String path = "https://currentmillis.com/images/milliseconds.png";
        ReconhecimentoRequestDTO reconhecimentoRequestDTO = new ReconhecimentoRequestDTO(deviceKey, personId, time, ip, type, path);

        // Action
        ReconhecimentoResponseDTO response = reconhecimentoService.cadastrar(reconhecimentoRequestDTO);

        // Assert
        assertThat(response.getId()).isNotNull();
    }
}
