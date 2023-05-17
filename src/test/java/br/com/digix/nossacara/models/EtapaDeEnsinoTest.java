package br.com.digix.nossacara.models;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;



public class EtapaDeEnsinoTest {

    @Test
    void deve_criar_uma_etapaDeEnsino() {
        // Arange
        String nome = "Ensino Médio";

        // Action
        EtapaDeEnsino etapaDeEnsino =  EtapaDeEnsino.builder().nome(nome).build();

        //Asserts
        assertThat(etapaDeEnsino.getNome()).isEqualTo(nome);

    }
    
}
