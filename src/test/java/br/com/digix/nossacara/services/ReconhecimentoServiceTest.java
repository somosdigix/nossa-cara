package br.com.digix.nossacara.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import br.com.digix.nossacara.dtos.ReconhecimentoSucessResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.mappers.DataConverter;
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
        ReconhecimentoSucessResponseDTO response = reconhecimentoService.cadastrarRespostaOK(reconhecimentoRequestDTO);

        // Assert
        assertThat(response.getResult()).isEqualTo(1);
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    void deve_converter_string_milisegundos_para_timestamp() {
        // Arrange
        String milisegundos = "1677181801486";
        LocalDateTime dataEsperada = LocalDateTime.of(2023, 2, 23, 15, 50, 01);

        // Action
        LocalDateTime dataAtual = DataConverter.toDate(milisegundos);

        // Assert
        assertThat(dataAtual).isEqualTo(dataEsperada);
    }

    @Test
    void deve_salvar_data_formatada() throws Exception {
        // Arrange
        String milisegundos = "1677181801486";
        LocalDateTime dataEsperada = LocalDateTime.of(2023, 2, 23, 15, 50, 01);
        ReconhecimentoRequestDTO reconhecimentoRequestDTO = new ReconhecimentoRequestDTOBuilder().comTime(milisegundos)
                .construir();

        // Action
        ReconhecimentoResponseDTO response = reconhecimentoService.cadastrar(reconhecimentoRequestDTO);

        // Assert
        assertThat(response.getDataDeCriacao()).isEqualTo(dataEsperada);
        assertThat(response.getTime()).isEqualTo(milisegundos);
    }

    @Test
    void nao_deve_salvar_reconhecimentos_com_menos_de_5_minutos_de_diferenca() throws Exception {

        ReconhecimentoRequestDTO reconhecimentoRequestDTO = new ReconhecimentoRequestDTOBuilder()
                .comTime("1680116400000").construir();

        ReconhecimentoResponseDTO response = reconhecimentoService.cadastrar(reconhecimentoRequestDTO);

        assertThat(response.getId()).isNotNull();

        ReconhecimentoRequestDTO reconhecimentoRequestDTO2 = new ReconhecimentoRequestDTOBuilder()
                .comTime("1680116520000").construir();

        ReconhecimentoResponseDTO response2 = reconhecimentoService.cadastrar(reconhecimentoRequestDTO2);

        assertThat(response2.getId()).isNull();
    }

    @Test
    void deve_salvar_reconhecimentos_com_mais_de_5_minutos_de_diferenca() throws Exception {
        ReconhecimentoRequestDTO reconhecimentoRequestDTO = new ReconhecimentoRequestDTOBuilder()
                .comTime("1680803100000").construir();

        ReconhecimentoResponseDTO response = reconhecimentoService.cadastrar(reconhecimentoRequestDTO);

        assertThat(response.getId()).isNotNull();

        ReconhecimentoRequestDTO reconhecimentoRequestDTO2 = new ReconhecimentoRequestDTOBuilder()
                .comTime("1680803460000").construir();

        ReconhecimentoResponseDTO response2 = reconhecimentoService.cadastrar(reconhecimentoRequestDTO2);

        assertThat(response2.getId()).isNotNull();
    }

    @Test
    void nao_deve_salvar_reconhecimentos_com_exatamente_5_minutos_de_diferenca() throws Exception {
        ReconhecimentoRequestDTO reconhecimentoRequestDTO = new ReconhecimentoRequestDTOBuilder()
                .comTime("1680803100000").construir();

        ReconhecimentoResponseDTO response = reconhecimentoService.cadastrar(reconhecimentoRequestDTO);

        assertThat(response.getId()).isNotNull();

        ReconhecimentoRequestDTO reconhecimentoRequestDTO2 = new ReconhecimentoRequestDTOBuilder()
                .comTime("1680803400000").construir();

        ReconhecimentoResponseDTO response2 = reconhecimentoService.cadastrar(reconhecimentoRequestDTO2);

        assertThat(response2.getId()).isNull();
    }
}
