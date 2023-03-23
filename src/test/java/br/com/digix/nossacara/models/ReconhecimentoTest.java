package br.com.digix.nossacara.models;

import builders.ReconhecimentoBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

public class ReconhecimentoTest {

    @Test
    void deve_criar_um_reconhecimento() {
        // Arrange
        String deviceKey = "84E0F42";
        String personId = "63f7c32f305d6c3a53cfd502";
        LocalDateTime dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01) ;
        String ip = "192.168.11.2";
        String type = "face_0";
        String path = "https://currentmillis.com/images/milliseconds.png";

        // Action
        Reconhecimento reconhecimento =  new ReconhecimentoBuilder().construir();

        // Asserts
        assertThat(reconhecimento.getDeviceKey()).isEqualTo(deviceKey);
        assertThat(reconhecimento.getPersonId()).isEqualTo(personId);
        assertThat(reconhecimento.getDataDeCriacao()).isEqualTo(dataDeCriacao);
        assertThat(reconhecimento.getIp()).isEqualTo(ip);
        assertThat(reconhecimento.getType()).isEqualTo(type);
        assertThat(reconhecimento.getPath()).isEqualTo(path);
    }
}
