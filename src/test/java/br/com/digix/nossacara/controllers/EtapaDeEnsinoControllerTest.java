package br.com.digix.nossacara.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.assertj.core.api.Assertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import br.com.digix.nossacara.NossacaraApplication;
import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.models.EtapaDeEnsino;
import br.com.digix.nossacara.repository.EtapaDeEnsinoRepository;
import br.com.digix.nossacara.utils.JsonUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = NossacaraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@WithMockUser
public class EtapaDeEnsinoControllerTest {
    @Autowired
    private EtapaDeEnsinoRepository etapaDeEnsinoRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @AfterEach
    public void deletaDados() {
        etapaDeEnsinoRepository.deleteAll();
    }

    @Test
    void deve_buscar_uma_etapa_de_ensino_pelo_id() throws Exception {
        EtapaDeEnsino etapaDeEnsino = new EtapaDeEnsino("Ensino Medio");
        etapaDeEnsinoRepository.save(etapaDeEnsino);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/etapas-de-ensino/" + etapaDeEnsino.getId())).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        EtapaDeEnsinoResponseDTO etapaDeEnsinoDTO = JsonUtil.mapFromJson(content,
                EtapaDeEnsinoResponseDTO.class);

        Assertions.assertThat(etapaDeEnsino.getId()).isEqualTo(etapaDeEnsinoDTO.getId());
    }
}
