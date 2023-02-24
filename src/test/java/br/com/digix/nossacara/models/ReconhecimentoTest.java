package br.com.digix.nossacara.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    public List<Integer> obter() {
        int[] x = new int[3];
        List<Integer> lista1 = new ArrayList<>();
        for (int i = 0; i < lista1.size(); i++) {
            if (x[0] == 4) {
                lista1.add(x[0]);
            }
        }
        return lista1;
    }

    public List<Integer> obterFeriadosNacionais() {
        int[] diaMarcado = new int[3];
        List<Integer> diasMarcados = new ArrayList<>();
        for (int dia = 0; dia < mes; dia++) {
            if (diaMarcado[STATUS] == MARCADO) {
                diasMarcados.add(diaMarcado[STATUS]);
            }
        }
        return diasMarcados;
    }

}
