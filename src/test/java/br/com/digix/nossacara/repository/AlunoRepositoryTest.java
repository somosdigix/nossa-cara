package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;

    @Autowired
    EtapaDeEnsinoRepository etapaDeEnsinoRepository;


    @BeforeEach
    void setUp() {
        alunoRepository.deleteAll();
    }

    @Test
    void deve_buscar_alunos_com_reconhecimento_no_dia() {
        // Arrange
        String deviceKey = "1";
        LocalDate data = LocalDate.of(2023, 2, 23);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Escola escola = new Escola("E E Lucia Martins Coelho", 10);
        escolaRepository.save(escola);
        EtapaDeEnsino etapaDeEnsino = new EtapaDeEnsino("Ensino Médio");
        etapaDeEnsinoRepository.save(etapaDeEnsino);
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
        localDeEntradaRepository.save(localDeEntrada);
        escola.setLocaisDeEntrada(List.of(localDeEntrada));


        Aluno aluno1 = Aluno.builder().nome("Tiago").turma("1°").turno("matutino")
                .personId("1").build();
        Aluno aluno2 = Aluno.builder().nome("Kaio").turma("1°").turno("matutino")
                .personId("2").build();
        Aluno aluno3 = Aluno.builder().nome("Flávio").turma("1°").turno("matutino")
                .personId("3").build();
        Aluno aluno4 = Aluno.builder().nome("Sérgio").turma("1°").turno("matutino")
                .personId("4").build();
        Aluno aluno5 = Aluno.builder().nome("Enzo").turma("1°").turno("matutino")
                .personId("5").build();
        alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5));

        List<Aluno> alunosEsperados = alunoRepository.findAll();
        alunosEsperados.sort(Comparator.comparing(Aluno::getNome));
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
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3, reconhecimento4, reconhecimento5));
        // Action
        Page<Aluno> alunosRetornados = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, "", etapaDeEnsino.getId(), data, PageRequest.of(1, 5));
        assertThat(alunosRetornados.getContent()).isEqualTo(alunosEsperados);

    }

    @Test
    void nao_deve_retornar_alunos_se_for_uma_pagina_sem_alunos() {
        // Arrange
        String deviceKey = "1";
        LocalDate data = LocalDate.of(2023, 2, 23);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Escola escola = new Escola("E E Lucia Martins Coelho", 10);
        escolaRepository.save(escola);
        EtapaDeEnsino etapaDeEnsino = new EtapaDeEnsino("Ensino Médio");
        etapaDeEnsinoRepository.save(etapaDeEnsino);
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
        localDeEntradaRepository.save(localDeEntrada);
        escola.setLocaisDeEntrada(List.of(localDeEntrada));
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
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3, reconhecimento4, reconhecimento5));
        // Action
        Page<Aluno> alunosRetornados = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, "", etapaDeEnsino.getId(), data, PageRequest.of(2, 5));
        // Asserts
        assertThat(alunosRetornados.getContent()).isEmpty();
    }

    @Test
    void deve_retornar_alunos_de_acordo_com_o_tamanho_da_pagina() {
        // Arrange
        String deviceKey = "1";
        LocalDate data = LocalDate.of(2023, 2, 23);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Escola escola = new Escola("E E Lucia Martins Coelho", 10);
        escolaRepository.save(escola);
        EtapaDeEnsino etapaDeEnsino = new EtapaDeEnsino("Ensino Médio");
        etapaDeEnsinoRepository.save(etapaDeEnsino);
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
        localDeEntradaRepository.save(localDeEntrada);
        escola.setLocaisDeEntrada(List.of(localDeEntrada));
        Aluno aluno1 = Aluno.builder().nome("Tiago").turma("1°").turno("matutino")
                .personId("1").build();
        Aluno aluno2 = Aluno.builder().nome("Kaio").turma("1°").turno("matutino")
                .personId("2").build();
        Aluno aluno3 = Aluno.builder().nome("Flávio").turma("1°").turno("matutino")
                .personId("3").build();
        Aluno aluno4 = Aluno.builder().nome("Sérgio").turma("1°").turno("matutino")
                .personId("4").build();
        Aluno aluno5 = Aluno.builder().nome("Enzo").turma("1°").turno("matutino")
                .personId("5").build();
        alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5));
        List<Aluno> alunosEsperados = Arrays.asList(aluno5, aluno3, aluno2);
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
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3, reconhecimento4, reconhecimento5));
        // Action
        Page<Aluno> alunosRetornados = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, "", etapaDeEnsino.getId(), data, PageRequest.of(1, 3));
        // Asserts
        assertThat(alunosRetornados.getContent()).isEqualTo(alunosEsperados);

    }

    @Test
    void deve_retornar_aluno_de_acordo_com_a_busca() {
        // Arrange
        String deviceKey = "1";
        LocalDate data = LocalDate.of(2023, 2, 23);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Escola escola = new Escola("E E Lucia Martins Coelho", 10);
        escolaRepository.save(escola);
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
        localDeEntradaRepository.save(localDeEntrada);
        escola.setLocaisDeEntrada(List.of(localDeEntrada));
        EtapaDeEnsino etapaDeEnsino = new EtapaDeEnsino("Ensino Médio");
        etapaDeEnsinoRepository.save(etapaDeEnsino);
        Aluno aluno1 = Aluno.builder().nome("Tiago").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("1").build();
        Aluno aluno2 = Aluno.builder().nome("Kaio").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("2").build();
        Aluno aluno3 = Aluno.builder().nome("Flávio").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("3").build();
        Aluno aluno4 = Aluno.builder().nome("Sérgio").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("4").build();
        Aluno aluno5 = Aluno.builder().nome("Enzo").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("5").build();
        alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5));
        List<Aluno> alunoEsperado = Collections.singletonList(aluno1);
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
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3, reconhecimento4, reconhecimento5));
        // Action
        Page<Aluno> alunosRetornados = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, "Tiago", etapaDeEnsino.getId(), data, PageRequest.of(1, 3));
        // Asserts
        assertThat(alunosRetornados.getContent()).isEqualTo(alunoEsperado);

    }

    @Test
    void deve_retornar_aluno_de_acordo_com_a_busca_pelo_nome_parcial() {
        // Arrange
        String deviceKey = "1";
        LocalDate data = LocalDate.of(2023, 2, 23);
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        Escola escola = new Escola("E E Lucia Martins Coelho", 10);
        escolaRepository.save(escola);
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
        localDeEntradaRepository.save(localDeEntrada);
        escola.setLocaisDeEntrada(List.of(localDeEntrada));
        EtapaDeEnsino etapaDeEnsino = new EtapaDeEnsino("Ensino Médio");
        etapaDeEnsinoRepository.save(etapaDeEnsino);
        Aluno aluno1 = Aluno.builder().nome("Tiago").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("1").build();
        Aluno aluno2 = Aluno.builder().nome("Kaio").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("2").build();
        Aluno aluno3 = Aluno.builder().nome("Flávio").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("3").build();
        Aluno aluno4 = Aluno.builder().nome("Sérgio").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("4").build();
        Aluno aluno5 = Aluno.builder().nome("Enzo").etapaDeEnsino(etapaDeEnsino).turma("1°").turno("matutino")
                .personId("5").build();
        alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5));
        List<Aluno> alunosEsperados = Arrays.asList(aluno3, aluno2, aluno4);
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
        reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1, reconhecimento2, reconhecimento3, reconhecimento4, reconhecimento5));
        // Action
        Page<Aluno> alunosRetornados = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, "io", etapaDeEnsino.getId(), data, PageRequest.of(1, 3));
        // Asserts
        assertThat(alunosRetornados.getContent()).isEqualTo(alunosEsperados);

    }

}
