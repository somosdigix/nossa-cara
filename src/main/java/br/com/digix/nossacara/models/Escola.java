package br.com.digix.nossacara.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Escola {
    @Column
    @Id
    private long id;

    @Column
    private String nome;

    @Column
    private int quantidadeAlunos;
}