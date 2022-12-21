package br.com.digix.nossacara.models;

public class ReconhecimentoBuilder {

    private String deviceKey;
    private Long personId;
    private Long time;
    private String ip;
    private String type;
    private String path;

    public ReconhecimentoBuilder() {
        this.deviceKey = "84E0F42";
        this.personId = 999L;
        this.time = 1651145957787L;
        this.ip = "192.168.11.2";
        this.type = "face_0";
        this.path = "https://currentmillis.com/images/milliseconds.png";
    }

    public Reconhecimento construir() throws Exception {
        return new Reconhecimento(deviceKey, personId, time, ip, type, path);
    }
}
