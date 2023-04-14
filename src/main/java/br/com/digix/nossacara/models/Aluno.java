package br.com.digix.nossacara.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Aluno {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String nome;
    private String etapaDeEnsino;
    private String turma;
    private String turno;
    private String personId;

    @ManyToOne
    private Escola escola;

    public Aluno(String nome, String etapaDeEnsino, String turma, String turno, String personId, Escola escola) {

        this.nome = nome;
        this.etapaDeEnsino = etapaDeEnsino;
        this.turma = turma;
        this.turno = turno;
        this.personId = personId;
        this.escola = escola;
    }
}
