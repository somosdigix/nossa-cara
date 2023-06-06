package br.com.digix.nossacara.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @JoinColumn(name = "escola_id")
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