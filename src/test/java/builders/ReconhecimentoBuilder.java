package builders;

import java.time.LocalDateTime;

import br.com.digix.nossacara.models.Reconhecimento;

public class ReconhecimentoBuilder {

    private String deviceKey;
    private String personId;
    private LocalDateTime dataDeCriacao;
    private String ip;
    private String type;
    private String path;

    public ReconhecimentoBuilder() {
        this.deviceKey = "84E0F42";
        this.personId = "63f7c32f305d6c3a53cfd502";
        this.dataDeCriacao = LocalDateTime.of(2023, 2, 23, 19, 50, 01);
        this.ip = "192.168.11.2";
        this.type = "face_0";
        this.path = "https://currentmillis.com/images/milliseconds.png";
    }

    public Reconhecimento construir(){
        return new Reconhecimento(deviceKey, personId, dataDeCriacao, ip, type, path);
    }
}
