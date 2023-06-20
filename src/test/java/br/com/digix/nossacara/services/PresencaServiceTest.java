package br.com.digix.nossacara.services;

import br.com.digix.nossacara.dtos.EntradaResponseDTO;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PresencaServiceTest {

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private PresencaService presencaService;
    
    @Test
    void deve_calcular_a_entrada_de_alunos_em_uma_escola_em_um_dia() {
        // Arrange
        String deviceKey = "1";
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Reconhecimento reconhecimento = new Reconhecimento(deviceKey, "1", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento2 = new Reconhecimento(deviceKey, "2", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento3 = new Reconhecimento(deviceKey, "3", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento4 = new Reconhecimento(deviceKey, "4", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento5 = new Reconhecimento(deviceKey, "5", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento6 = new Reconhecimento(deviceKey, "6", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento7 = new Reconhecimento(deviceKey, "7", dataDeCriacao, "192.168.11.2", "1677181801486", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento, reconhecimento2, reconhecimento3,
                reconhecimento4, reconhecimento5, reconhecimento6, reconhecimento7));

        Escola escola = new Escola("E E Lucia Martins Coelho", 10);
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
        escola.setLocaisDeEntrada(Collections.singletonList(localDeEntrada));
        LocalDate dia = LocalDate.of(2023, 2, 23);
        // Action
        EntradaResponseDTO entrada = presencaService.buscarComparecimentoEntrada(dia, escola);
        // Assert
        assertThat(entrada.getQuantidadeEntrada()).isEqualTo(7);
        assertThat(entrada.getQuantidadeAusente()).isEqualTo(3);
    }
}
