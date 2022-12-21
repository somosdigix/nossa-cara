package br.com.digix.nossacara.models;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class ReconhecimentoTest {

    @Test
    void deve_criar_um_reconhecimento() {
        // Arrange
        String deviceKey = "84E0F42";
        Long personId = 999L;
        Long time = 1651145957787L;
        String ip = "192.168.11.2";
        String type = "face_0";
        String path = "https://currentmillis.com/images/milliseconds.png";

        // Action
        Reconhecimento reconhecimento = new Reconhecimento(deviceKey, personId, time, ip, type, path);

        // Asserts
        assertThat(reconhecimento.getDeviceKey()).isEqualTo(deviceKey);
        assertThat(reconhecimento.getPersonId()).isEqualTo(personId);
        assertThat(reconhecimento.getTime()).isEqualTo(time);
        assertThat(reconhecimento.getIp()).isEqualTo(ip);
        assertThat(reconhecimento.getType()).isEqualTo(type);
        assertThat(reconhecimento.getPath()).isEqualTo(path);
    }
    
}
