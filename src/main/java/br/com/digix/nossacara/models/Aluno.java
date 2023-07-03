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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String turma;
    private String turno;
    private String personId;
    @ManyToOne
    private EtapaDeEnsino etapaDeEnsino;
    @ManyToOne
    @JoinColumn(name = "escola_id", columnDefinition = "numeric(19,0)")
    private Escola escola;

    public Aluno(String nome,  String turma, String turno, String personId, Escola escola, EtapaDeEnsino etapaDeEnsino) {
        this.nome = nome;
        this.turma = turma;
        this.turno = turno;
        this.personId = personId;
        this.escola = escola;
        this.etapaDeEnsino = etapaDeEnsino;
    }
}