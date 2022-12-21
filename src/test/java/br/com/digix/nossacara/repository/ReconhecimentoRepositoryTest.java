package br.com.digix.nossacara.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.digix.nossacara.models.Reconhecimento;

@DataJpaTest
public class ReconhecimentoRepositoryTest {

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @BeforeEach
    void setUp() {
        reconhecimentoRepository.deleteAll();
    }
    
    @Test
    void deve_salvar_um_reconhecimento() {
        // Arrange
        String deviceKey = "84E0F42";
        Long personId = 999L;
        Long time = 1651145957787L;
        String ip = "192.168.11.2";
        String type = "face_0";
        String path = "https://currentmillis.com/images/milliseconds.png";
        Reconhecimento reconhecimento = new Reconhecimento(deviceKey, personId, time, ip, type, path);

        // Action
        reconhecimentoRepository.save(reconhecimento);

        // Asserts
        assertThat(reconhecimento.getId()).isNotNull();
    }
}
