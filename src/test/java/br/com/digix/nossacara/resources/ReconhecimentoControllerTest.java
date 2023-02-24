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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    void deve_cadastrar_um_reconhecimento() throws Exception {
        // Arrange
        int quantidadeEsperada = 1;
        String deviceKey = "84E0F42";
        String personId = "63f7c32f305d6c3a53cfd502";
        String time = "1651145957787";
        String ip = "192.168.11.2";
        String type = "face_0";
        String path = "https://currentmillis.com/images/milliseconds.png";
        ReconhecimentoRequestDTO reconhecimentoRequestDTO = new ReconhecimentoRequestDTO(deviceKey, personId, time, ip, type, path);
        
        mvc.perform(post("/api/v1/reconhecimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(reconhecimentoRequestDTO)))
                .andExpect(status().isOk());

        Iterable<Reconhecimento> reconhecimentosEncontrados = reconhecimentoRepository.findAll();
        long quantidadeEncontrada = reconhecimentosEncontrados.spliterator().getExactSizeIfKnown();

        assertThat(quantidadeEncontrada).isEqualTo(quantidadeEsperada);
        assertThat(reconhecimentosEncontrados).extracting(Reconhecimento::getDeviceKey).containsOnly(deviceKey);
    }

    @Test
    void deve_buscar_todos() throws Exception{
        criarReconhecimento();
        criarReconhecimento();

        this.mvc.perform(get("/api/v1/reconhecimentos"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("84E0F42")));
    }

    private void criarReconhecimento() {
        String deviceKey = "84E0F42";
        String personId = "63f7c32f305d6c3a53cfd502";
        Long time = 1651145957787L;
        String ip = "192.168.11.2";
        String type = "face_0";
        String path = "https://currentmillis.com/images/milliseconds.png";
        Reconhecimento reconhecimento = new Reconhecimento(deviceKey, personId, time, ip, type, path);
        reconhecimentoRepository.save(reconhecimento);
    }
}
