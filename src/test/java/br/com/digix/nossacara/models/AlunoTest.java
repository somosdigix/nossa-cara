package br.com.digix.nossacara.models;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AlunoTest {
    @Test
     void deve_criar_um_aluno(){
        //Arrange
        String nome = "juninho";
        String etapaDeEnsino = "Ensino fundamental";
        String turma = "1°";
        String turno = "integral";
        String personId = "63f7c32f305d6c3a53cfd502";

        //Action
        Aluno aluno = Aluno.builder().nome(nome).etapaDeEnsino(etapaDeEnsino).turma(turma).turno(turno).personId(personId).
        build();

        //Asserts
        assertThat(aluno.getNome()).isEqualTo(nome);
        assertThat(aluno.getEtapaDeEnsino()).isEqualTo(etapaDeEnsino);
        assertThat(aluno.getTurma()).isEqualTo(turma);
        assertThat(aluno.getTurno()).isEqualTo(turno);
        assertThat(aluno.getPersonId()).isEqualTo(personId);

    }

    @Test
    void deve_criar_um_aluno_com_nome(){
        //Arrange
        String nome = "junior";

        //Action
        Aluno aluno = Aluno.builder().nome(nome).build();

        //Asserts
        assertThat(aluno.getNome()).isEqualTo(nome);


    }

    
}
