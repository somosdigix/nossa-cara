package br.com.digix.nossacara.resources;

import br.com.digix.nossacara.NossacaraApplication;
import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import br.com.digix.nossacara.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = NossacaraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ReconhecimentoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @BeforeEach
    void resetDb() {
        reconhecimentoRepository.deleteAll();
    }


    @Test
    void deve_cadastrar_um_reconhecimento() throws IOException, Exception {
        // Arrange
        int quantidadeEsperada = 1;
        String deviceKey = "84E0F42";
        String personId = "999";
        String time = "1651145957787";
        String ip = "192.168.11.2";
        String type = "face_0";
        String path = "https://currentmillis.com/images/milliseconds.png";
        ReconhecimentoRequestDTO reconhecimentoRequestDTO = new ReconhecimentoRequestDTO(deviceKey, personId, time, ip, type, path);
        
        mvc.perform(post("/api/v1/reconhecimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(reconhecimentoRequestDTO)))
                .andExpect(status().isCreated());

        Iterable<Reconhecimento> reconhecimentosEncontrados = reconhecimentoRepository.findAll();
        long quantidadeEncontrada = reconhecimentosEncontrados.spliterator().getExactSizeIfKnown();

        assertThat(quantidadeEncontrada).isEqualTo(quantidadeEsperada);
        assertThat(reconhecimentosEncontrados).extracting(Reconhecimento::getDeviceKey).containsOnly(deviceKey);
    }
}
