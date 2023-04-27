package br.com.digix.nossacara.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Reconhecimento;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;

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
        LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
        localDeEntradaRepository.save(localDeEntrada);
        escola.setLocaisDeEntrada(Arrays.asList(localDeEntrada));
        

        Aluno aluno1 = Aluno.builder().nome("Tiago").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
                .personId("1").build();
        Aluno aluno2 = Aluno.builder().nome("Kaio").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
                .personId("2").build();
        Aluno aluno3 = Aluno.builder().nome("Flávio").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
                .personId("3").build();
        Aluno aluno4 = Aluno.builder().nome("Sérgio").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
                .personId("4").build();
        Aluno aluno5 = Aluno.builder().nome("Enzo").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
                .personId("5").build();
                alunoRepository.saveAll(Arrays.asList(aluno1,aluno2,aluno3,aluno4,aluno5));
                
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
                reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1,reconhecimento2,reconhecimento3,reconhecimento4,reconhecimento5));
        // Action
        List<Aluno> alunosRetornados = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, data, PageRequest.of(1, 5));
        // Asserts
        assertThat(alunosRetornados).isEqualTo(alunosEsperados);

    }

    @Test
    void nao_deve_retornar_alunos_se_for_uma_pagina_sem_alunos() {
          // Arrange
          String deviceKey = "1";
          LocalDate data = LocalDate.of(2023, 2, 23);
          LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
          Escola escola = new Escola("E E Lucia Martins Coelho", 10);
          escolaRepository.save(escola);
          LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
          localDeEntradaRepository.save(localDeEntrada);
          escola.setLocaisDeEntrada(Arrays.asList(localDeEntrada));
          List<Aluno> alunosEsperados = alunoRepository.findAll();
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
                  reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1,reconhecimento2,reconhecimento3,reconhecimento4,reconhecimento5));
          // Action
          List<Aluno> alunosRetornados = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, data, PageRequest.of(2, 5));
          // Asserts
          assertThat(alunosRetornados).isEmpty();
    }

    @Test
    void deve_retornar_alunos_de_acordo_com_o_tamanho_da_pagina() {
     // Arrange
     String deviceKey = "1";
     LocalDate data = LocalDate.of(2023, 2, 23);
     LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
     Escola escola = new Escola("E E Lucia Martins Coelho", 10);
     escolaRepository.save(escola);
     LocalDeEntrada localDeEntrada = new LocalDeEntrada(deviceKey, "entradaPrincipal", escola);
     localDeEntradaRepository.save(localDeEntrada);
     escola.setLocaisDeEntrada(Arrays.asList(localDeEntrada));
     Aluno aluno1 = Aluno.builder().nome("Tiago").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
             .personId("1").build();
     Aluno aluno2 = Aluno.builder().nome("Kaio").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
             .personId("2").build();
     Aluno aluno3 = Aluno.builder().nome("Flávio").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
             .personId("3").build();
     Aluno aluno4 = Aluno.builder().nome("Sérgio").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
             .personId("4").build();
     Aluno aluno5 = Aluno.builder().nome("Enzo").etapaDeEnsino("Ensino medio").turma("1°").turno("matutino")
             .personId("5").build();
             alunoRepository.saveAll(Arrays.asList(aluno1,aluno2,aluno3,aluno4,aluno5));
             List<Aluno> alunosEsperados = Arrays.asList(aluno5,aluno3,aluno2);
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
             reconhecimentoRepository.saveAll(Arrays.asList(reconhecimento1,reconhecimento2,reconhecimento3,reconhecimento4,reconhecimento5));
     // Action
     List<Aluno> alunosRetornados = alunoRepository.buscarAlunosComReconhecimentoNoDia(escola, data, PageRequest.of(1, 3));
     // Asserts
     assertThat(alunosRetornados).isEqualTo(alunosEsperados);

    }

}
