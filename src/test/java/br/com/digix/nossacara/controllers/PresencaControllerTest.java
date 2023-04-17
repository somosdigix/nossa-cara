package br.com.digix.nossacara.controllers;

import br.com.digix.nossacara.NossacaraApplication;
import br.com.digix.nossacara.dtos.EntradaResponseDTO;
import br.com.digix.nossacara.dtos.PresencaResponseDTO;
import br.com.digix.nossacara.dtos.RefeitorioResponseDTO;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.models.Refeitorio;
import br.com.digix.nossacara.repository.EscolaRepository;
import br.com.digix.nossacara.repository.LocalDeEntradaRepository;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import br.com.digix.nossacara.repository.RefeitorioRepository;
import br.com.digix.nossacara.utils.JsonUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = NossacaraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PresencaControllerTest {

    public static final String DEVICE_KEY = "1";
    public static final String NUMERO_DISPOSITIVO = "84E0F4210D4C607A";
    public static final LocalDateTime DATA_DE_CRIACAO = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private RefeitorioRepository refeitorioRepository;

    @BeforeEach
    @AfterEach
    public void deletaDados() {
        reconhecimentoRepository.deleteAll();
    }

    @Test
    void deve_buscar_os_comparecimentos_pelo_dia() throws Exception {
        Reconhecimento reconhecimento1 = criarReconhecimento(DEVICE_KEY, "1");
        Reconhecimento reconhecimento2 = criarReconhecimento(DEVICE_KEY, "2");
        Reconhecimento reconhecimento3 = criarReconhecimento(DEVICE_KEY, "3");
        Reconhecimento reconhecimento4 = criarReconhecimento(DEVICE_KEY, "4");
        Reconhecimento reconhecimento5 = criarReconhecimento(DEVICE_KEY, "5");
        Reconhecimento reconhecimento6 = criarReconhecimento(DEVICE_KEY, "6");
        Reconhecimento reconhecimento7 = criarReconhecimento(DEVICE_KEY, "7");
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3,
                reconhecimento4, reconhecimento5, reconhecimento6, reconhecimento7));

        LocalDeEntrada localDeEntrada = new LocalDeEntrada(1L, DEVICE_KEY, "entradaPrincipal");
        localDeEntradaRepository.save(localDeEntrada);
        Escola escola = new Escola(1, "E E Lucia Martins Coelho", 10, Collections.singleton(localDeEntrada));
        escolaRepository.save(escola);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/presencas/entradas" + "?dia=" + "2023-02-23"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        EntradaResponseDTO entradaDTO = JsonUtil.mapFromJson(content, EntradaResponseDTO.class);

        Assertions.assertThat(entradaDTO.getQuantidadeEntrada()).isEqualTo(7);
        Assertions.assertThat(entradaDTO.getQuantidadeAusente()).isEqualTo(3);
    }

    private static Reconhecimento criarReconhecimento(String deviceKey, String personId) {
        return new Reconhecimento(deviceKey, personId, PresencaControllerTest.DATA_DE_CRIACAO, "192.168.11.2", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
    }

    @Test
    public void deve_Buscar_Todas_Presencas() throws Exception {
        Reconhecimento reconhecimento1 = criarReconhecimento(DEVICE_KEY, "1");
        Reconhecimento reconhecimento2 = criarReconhecimento(DEVICE_KEY, "2");
        Reconhecimento reconhecimento3 = criarReconhecimento(PresencaControllerTest.NUMERO_DISPOSITIVO, "3");
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3));

        Refeitorio refeitorio = new Refeitorio(1L, PresencaControllerTest.NUMERO_DISPOSITIVO, "Refeitorio Central");
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(1L, DEVICE_KEY, "entradaPrincipal");
        localDeEntradaRepository.save(localDeEntrada);
        Escola escola = new Escola(1, "E E Lucia Martins Coelho", 10, Collections.singleton(localDeEntrada));
        escolaRepository.save(escola);
        refeitorioRepository.save(refeitorio);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/presencas" + "?dia=" + "2023-02-23"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        PresencaResponseDTO presencaDTO = JsonUtil.mapFromJson(content, PresencaResponseDTO.class);

        Assertions.assertThat(presencaDTO.getQuantidadeEntradaEscola()).isEqualTo(2);
        Assertions.assertThat(presencaDTO.getQuantidadeEntradaRefeitorio()).isEqualTo(1);
    }

    @Test
    public void deve_Buscar_Presencas_no_Refeitorio() throws Exception {
        Reconhecimento reconhecimento1 = criarReconhecimento(NUMERO_DISPOSITIVO, "1");
        Reconhecimento reconhecimento2 = criarReconhecimento(NUMERO_DISPOSITIVO, "2");
        Reconhecimento reconhecimento3 = criarReconhecimento(NUMERO_DISPOSITIVO, "3");
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3));

        Refeitorio refeitorio = new Refeitorio(1L, NUMERO_DISPOSITIVO, "Refeitorio Central");
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(1L, NUMERO_DISPOSITIVO, "entradaPrincipal");
        localDeEntradaRepository.save(localDeEntrada);
        Escola escola = new Escola(1, "E E Lucia Martins Coelho", 10, Collections.singleton(localDeEntrada));
        escolaRepository.save(escola);
        refeitorioRepository.save(refeitorio);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/presencas/refeitorio" + "?dia=" + "2023-02-23"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        RefeitorioResponseDTO presencaDTO = JsonUtil.mapFromJson(content, RefeitorioResponseDTO.class);

        Assertions.assertThat(presencaDTO.getQuantidadeEntrada()).isEqualTo(3);
    }

    @Test
    public void deve_Buscar_Presencas_na_Entrada() throws Exception {
        Reconhecimento reconhecimento1 = criarReconhecimento(NUMERO_DISPOSITIVO, "1");
        Reconhecimento reconhecimento2 = criarReconhecimento(NUMERO_DISPOSITIVO, "2");
        Reconhecimento reconhecimento3 = criarReconhecimento(NUMERO_DISPOSITIVO, "3");
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3));

        LocalDeEntrada localDeEntrada = new LocalDeEntrada(1L, NUMERO_DISPOSITIVO, "entradaPrincipal");
        localDeEntradaRepository.save(localDeEntrada);
        Escola escola = new Escola(1, "E E Lucia Martins Coelho", 10, Collections.singleton(localDeEntrada));
        escolaRepository.save(escola);


        MvcResult mvcResult = mockMvc.perform(get("/api/v1/presencas/entradas" + "?dia=" + "2023-02-23"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        EntradaResponseDTO presencaDTO = JsonUtil.mapFromJson(content, EntradaResponseDTO.class);

        Assertions.assertThat(presencaDTO.getQuantidadeEntrada()).isEqualTo(3);
    }

}
