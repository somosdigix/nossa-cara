package br.com.digix.nossacara.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Escola {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int quantidadeAlunos;

    @OneToMany(mappedBy = "escola")
    private Collection<Aluno> aluno;

    @OneToMany(mappedBy = "escola")
    private List<LocalDeEntrada> locaisDeEntrada;

    @OneToMany(mappedBy = "escola")
    private Collection<Refeitorio> refeitorios;

    public Escola(String nome, int quantidadeAlunos) {
        this.nome = nome;
        this.quantidadeAlunos = quantidadeAlunos;
    }
}
