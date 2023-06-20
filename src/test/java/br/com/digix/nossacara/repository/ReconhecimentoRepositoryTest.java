package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Reconhecimento;
import builders.ReconhecimentoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

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

    @Test
    void deve_retornar_a_quantidade_de_reconhecimentos_distintos_feitos_no_dia_em_um_dispositivo() {
        // Arrange
        int quantidadeEsperada = 3;
        String deviceKey = "1";
        String deviceKey2 = "2";
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Reconhecimento reconhecimento = new Reconhecimento(deviceKey, "1", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento2 = new Reconhecimento(deviceKey, "1", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento3 = new Reconhecimento(deviceKey, "1", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento4 = new Reconhecimento(deviceKey, "2", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento5 = new Reconhecimento(deviceKey, "3", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento6 = new Reconhecimento(deviceKey2, "3", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento7 = new Reconhecimento(deviceKey2, "4", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento, reconhecimento2, reconhecimento3,
                reconhecimento4, reconhecimento5, reconhecimento6, reconhecimento7));
        LocalDate dia = LocalDate.of(2023, 2, 23);
        LocalDeEntrada localDeEntrada = LocalDeEntrada.builder().nome("entradaPrincipal").numeroDispositivo(deviceKey)
                .build();
        // Action
        int quantidade = reconhecimentoRepository.quantidadeDeReconhecimentosDistintos(dia,
                Collections.singletonList(localDeEntrada.getNumeroDispositivo()));

        // Asserts
        assertThat(quantidade).isEqualTo(quantidadeEsperada);

    }


}
