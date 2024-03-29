package br.com.digix.nossacara.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LocalDeEntrada {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroDispositivo;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;

    public LocalDeEntrada(String numeroDispositivo, String nome, Escola escola) {
        this.numeroDispositivo = numeroDispositivo;
        this.nome = nome;
        this.escola = escola;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
}
