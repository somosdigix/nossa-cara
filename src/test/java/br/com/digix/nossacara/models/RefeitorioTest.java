package br.com.digix.nossacara.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RefeitorioTest {

    @Test
    void deve_conter_o_numero_do_dispositivo() {
        String numeroDispositivo = "84E0F4210D4C607A";
        String nome = "Refeitorio Central";
        Refeitorio refeitorioCentral =
                Refeitorio
                        .builder()
                        .numeroDispositivo(numeroDispositivo)
                        .nome(nome)
                        .build();

        assertThat(refeitorioCentral.getNumeroDispositivo()).isEqualTo(numeroDispositivo);
        assertThat(refeitorioCentral.getNome()).isEqualTo(nome);
    }
}
