package br.com.digix.nossacara.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;
import java.util.Arrays;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = NossacaraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PresencaControllerTest {

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
        String deviceKey = "1";
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Reconhecimento reconhecimento1 = new Reconhecimento(deviceKey, "1", dataDeCriacao, "192.168.11.2", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento2 = new Reconhecimento(deviceKey, "2", dataDeCriacao, "192.168.11.2", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento3 = new Reconhecimento(deviceKey, "3", dataDeCriacao, "192.168.11.2", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento4 = new Reconhecimento(deviceKey, "4", dataDeCriacao, "192.168.11.2", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento5 = new Reconhecimento(deviceKey, "5", dataDeCriacao, "192.168.11.2", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento6 = new Reconhecimento(deviceKey, "6", dataDeCriacao, "192.168.11.2", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        Reconhecimento reconhecimento7 = new Reconhecimento(deviceKey, "7", dataDeCriacao, "192.168.11.2", "face_0",
                "https://currentmillis.com/images/milliseconds.png");
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3,
                reconhecimento4, reconhecimento5, reconhecimento6, reconhecimento7));

                Escola escola = new Escola(1, "E E Lucia Martins Coelho", 10);
                LocalDeEntrada localDeEntrada = new LocalDeEntrada(1L, deviceKey, "entradaPrincipal");
                localDeEntradaRepository.save(localDeEntrada);
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

        @Test
        public void deve_Buscar_Todas_Presencas() throws Exception {

                String deviceKey = "1";
                String numeroDispositivo = "84E0F4210D4C607A";
                LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);

                Reconhecimento reconhecimento1 = new Reconhecimento(deviceKey, "1", dataDeCriacao, "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                Reconhecimento reconhecimento2 = new Reconhecimento(deviceKey, "2", dataDeCriacao, "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                Reconhecimento reconhecimento3 = new Reconhecimento(numeroDispositivo, "3", dataDeCriacao,
                                "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3));

                Refeitorio refeitorio = new Refeitorio(1L, numeroDispositivo, "Refeitorio Central");
                LocalDeEntrada localDeEntrada = new LocalDeEntrada(1L, deviceKey, "entradaPrincipal");
                Escola escola = new Escola(1, "E E Lucia Martins Coelho", 10);
                localDeEntradaRepository.save(localDeEntrada);
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
                String numeroDispositivo = "84E0F4210D4C607A";
                LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);

                Reconhecimento reconhecimento1 = new Reconhecimento(numeroDispositivo, "1", dataDeCriacao,
                                "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                Reconhecimento reconhecimento2 = new Reconhecimento(numeroDispositivo, "2", dataDeCriacao,
                                "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                Reconhecimento reconhecimento3 = new Reconhecimento(numeroDispositivo, "3", dataDeCriacao,
                                "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3));

                Refeitorio refeitorio = new Refeitorio(1L, numeroDispositivo, "Refeitorio Central");
                LocalDeEntrada localDeEntrada = new LocalDeEntrada(1L, numeroDispositivo, "entradaPrincipal");
                Escola escola = new Escola(1, "E E Lucia Martins Coelho", 10);
                escolaRepository.save(escola);
                localDeEntradaRepository.save(localDeEntrada);
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
                String numeroDispositivo = "84E0F4210D4C607A";
                LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);

                Reconhecimento reconhecimento1 = new Reconhecimento(numeroDispositivo, "1", dataDeCriacao,
                                "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                Reconhecimento reconhecimento2 = new Reconhecimento(numeroDispositivo, "2", dataDeCriacao,
                                "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                Reconhecimento reconhecimento3 = new Reconhecimento(numeroDispositivo, "3", dataDeCriacao,
                                "192.168.11.2",
                                "face_0",
                                "https://currentmillis.com/images/milliseconds.png");
                reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3));

                LocalDeEntrada localDeEntrada = new LocalDeEntrada(1L, numeroDispositivo, "entradaPrincipal");
                Escola escola = new Escola(1, "E E Lucia Martins Coelho", 10);
                escolaRepository.save(escola);
                localDeEntradaRepository.save(localDeEntrada);


                MvcResult mvcResult = mockMvc.perform(get("/api/v1/presencas/entradas" + "?dia=" + "2023-02-23"))
                                .andReturn();

                int status = mvcResult.getResponse().getStatus();
                assertEquals(HttpStatus.OK.value(), status);

                String content = mvcResult.getResponse().getContentAsString();
                EntradaResponseDTO presencaDTO = JsonUtil.mapFromJson(content, EntradaResponseDTO.class);

                Assertions.assertThat(presencaDTO.getQuantidadeEntrada()).isEqualTo(3);
        }

}
