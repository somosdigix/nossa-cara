package br.com.digix.nossacara.controllers;

import br.com.digix.nossacara.NossacaraApplication;
import br.com.digix.nossacara.dtos.AlunoPresenteResponseDTO;
import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.models.*;
import br.com.digix.nossacara.repository.*;
import br.com.digix.nossacara.utils.JsonUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static br.com.digix.nossacara.controllers.PresencaControllerTest.criarReconhecimento;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NossacaraApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlunoControllerTest {

    public static final String DEVICE_KEY = "1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EtapaDeEnsinoRepository etapaDeEnsinoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    @BeforeEach
    @AfterEach
    public void deletaDados() {
        reconhecimentoRepository.deleteAll();
        localDeEntradaRepository.deleteAll();
        alunoRepository.deleteAll();
        escolaRepository.deleteAll();
    }

    @Test
    public void deve_retornar_alunos_presentes_no_dia() throws Exception {
        List<Aluno> alunosPresentes = new ArrayList<>();

        Reconhecimento reconhecimento1 = criarReconhecimento(DEVICE_KEY, "1");
        Reconhecimento reconhecimento2 = criarReconhecimento(DEVICE_KEY, "2");
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2));

        Escola escola = new Escola("E E Lucia Martins Coelho", 10);
        escolaRepository.save(escola);

        LocalDeEntrada localDeEntrada = new LocalDeEntrada(DEVICE_KEY, "entradaPrincipal", escola);
        localDeEntradaRepository.save(localDeEntrada);

        escola.setLocaisDeEntrada(Collections.singletonList(localDeEntrada));
        escolaRepository.save(escola);

        EtapaDeEnsino etapaDeEnsino = new EtapaDeEnsino("ensino medio");
        etapaDeEnsinoRepository.save(etapaDeEnsino);

        Aluno aluno1 = Aluno.builder().nome("carlin").personId(reconhecimento2.getPersonId()).etapaDeEnsino(etapaDeEnsino).escola(escola).build();
        Aluno aluno2 = Aluno.builder().nome("socorro").personId(reconhecimento1.getPersonId()).etapaDeEnsino(etapaDeEnsino).escola(escola).build();
        alunosPresentes.add(aluno1);
        alunosPresentes.add(aluno2);

        alunoRepository.saveAll(alunosPresentes);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/alunos/presenca" + "?dia=" + "23-02-2023"))
                .andExpect(status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        ListagemAlunosResponseDTO listagemAlunosResponseDTO = JsonUtil.mapFromJson(content, ListagemAlunosResponseDTO.class);

        assertThat(listagemAlunosResponseDTO.getAlunos()).hasSize(2);
        assertThat(listagemAlunosResponseDTO.getAlunos()).extracting(AlunoPresenteResponseDTO::getNome)
                .contains("carlin", "socorro");
    }


}








