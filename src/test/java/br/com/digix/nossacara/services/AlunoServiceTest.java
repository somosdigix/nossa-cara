package br.com.digix.nossacara.services;

import br.com.digix.nossacara.dtos.AlunoPresenteResponseDTO;
import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.dtos.PageInfoDTO;
import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.AlunoRepository;
import br.com.digix.nossacara.repository.EscolaRepository;
import br.com.digix.nossacara.repository.LocalDeEntradaRepository;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AlunoServiceTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;

    @Autowired
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        alunoRepository.deleteAll();
        localDeEntradaRepository.deleteAll();
        escolaRepository.deleteAll();
        reconhecimentoRepository.deleteAll();
    }

    @Test
    public void deve_retornar_os_alunos_inseridos() {
        // Arrange
        String deviceKey = "1";
        int totalAlunos = 5;
        LocalDate data = LocalDate.of(2023, 2, 23);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Escola escola = cadastrarEscola(deviceKey);
        List<Aluno> alunosEsperados = cadastrarAlunos(Arrays.asList("Enzo", "Flávio", "Kaio", "Sergio", "Tiago"),
                escola);
        criarReconhecimentos(deviceKey, dataDeCriacao, totalAlunos);

        int pageSize = 15;
        int currentPage = 1;

        // Action
        ListagemAlunosResponseDTO listagem = alunoService.criarListaAlunosPresentes(data, escola, currentPage,
                pageSize);

        // Asserts
        assertThat(listagem.getPageInfo()).isNotNull().satisfies(pageInfo -> {
            assertThat(pageInfo.getCurrentPage()).isEqualTo(currentPage);
            assertThat(pageInfo.getPageSize()).isEqualTo(pageSize);
            assertThat(pageInfo.getTotalPages()).isEqualTo(1);
            assertThat(pageInfo.getTotal()).isEqualTo(totalAlunos);
            assertThat(pageInfo.isHasNext()).isFalse();
            assertThat(pageInfo.isHasPrevious()).isFalse();
        });
    }


    @Test
    public void deve_retornar_a_informacao_da_pagina_de_alunos() {
        // Arrange
        String deviceKey = "1";
        int totalAlunos = 5;
        LocalDate data = LocalDate.of(2023, 2, 23);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Escola escola = cadastrarEscola(deviceKey);
        cadastrarAlunos(Arrays.asList("Enzo", "Fl�vio", "Kaio", "Sergio", "Tiago"), escola);
        criarReconhecimentos(deviceKey, dataDeCriacao, totalAlunos);

        int pageSize = 15;
        int currentPage = 1;

        PageInfoDTO pageInfoEsperado = PageInfoDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .totalPages(1)
                .total(totalAlunos)
                .hasNext(false)
                .hasPrevious(false)
                .build();

        // Action
        ListagemAlunosResponseDTO listagem = alunoService.criarListaAlunosPresentes(data, escola, currentPage,
                pageSize);

        // Asserts
        assertThat(listagem.getPageInfo()).isNotNull().isEqualTo(pageInfoEsperado);
    }

    private void criarReconhecimentos(String deviceKey, LocalDateTime dataDeCriacao, int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            Reconhecimento reconhecimento = new Reconhecimento(deviceKey, Integer.toString(i), dataDeCriacao,
                    "192.168.11.2", "face_0",
                    "https://currentmillis.com/images/milliseconds.png");
            reconhecimentoRepository.save(reconhecimento);
        }
    }

    private Escola cadastrarEscola(String deviceKey) {
        Escola escola = new Escola("E E Lucia Martins Coelho", 10);
        escolaRepository.save(escola);
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
        localDeEntradaRepository.save(localDeEntrada);
        escola.setLocaisDeEntrada(List.of(localDeEntrada));
        return escola;
    }

    private List<Aluno> cadastrarAlunos(List<String> nomes, Escola escola) {
        int contadorParaPersonId = 1;
        List<Aluno> alunosEsperados = new ArrayList<>();
        for (String nome : nomes) {
            Aluno aluno = Aluno.builder().nome(nome).etapaDeEnsino("Ensino medio").turma("1�").turno("matutino")
                    .personId(Integer.toString(contadorParaPersonId)).escola(escola).build();
            alunoRepository.save(aluno);
            alunosEsperados.add(aluno);
            contadorParaPersonId++;
        }
        alunosEsperados.sort(Comparator.comparing(Aluno::getNome));
        return alunosEsperados;
    }
}