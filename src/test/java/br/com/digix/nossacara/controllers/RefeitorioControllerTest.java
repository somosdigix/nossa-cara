package br.com.digix.nossacara.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import br.com.digix.nossacara.NossacaraApplication;
import br.com.digix.nossacara.models.Refeitorio;
import br.com.digix.nossacara.repository.RefeitorioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = NossacaraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RefeitorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RefeitorioRepository refeitorioRepository;

    @BeforeEach
    void resetDb() {
        refeitorioRepository.deleteAll();
    }

    @Test
    void deve_cadastrar_um_refeitorio() throws Exception {
        String numeroDispositivo = "84E0F4210D4C607A";
        String nome = "Refeitorio Central";

        Refeitorio refeitorio = Refeitorio
                .builder()
                .numeroDispositivo(numeroDispositivo)
                .nome(nome)
                .build();

        refeitorioRepository.saveAll(Collections.singleton(refeitorio));

        this.mockMvc
                .perform(get("/api/v1/refeitorios"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(nome)));
    }
}
