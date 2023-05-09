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
    private String turma;
    private String turno;
    private String personId;

    @ManyToOne
    @JoinColumn(name = "etapa_de_ensino_id")
    private EtapaDeEnsino etapaDeEnsino;

    @ManyToOne
    private Escola escola;

    public Aluno(String nome, EtapaDeEnsino etapaDeEnsino, String turma, String turno, String personId, Escola escola) {
        this.nome = nome;
        this.etapaDeEnsino = etapaDeEnsino;
        this.turma = turma;
        this.turno = turno;
        this.personId = personId;
        this.escola = escola;
    }
}