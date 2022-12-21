package builders;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;

public class ReconhecimentoRequestDTOBuilder {
    private String deviceKey = "84E0F42";
    private String personId = "999";
    private String time = "1651145957787";
    private String ip = "192.168.11.2";
    private String type = "face_0";
    private String path = "https://currentmillis.com/images/milliseconds.png";

    public ReconhecimentoRequestDTOBuilder comDeviceKey(String deviceKeyEsperada) {
        this.deviceKey = deviceKeyEsperada;
        return this;
    }

    public ReconhecimentoRequestDTOBuilder comPersonId(String personIdEsperado) {
        this.personId = personIdEsperado;
        return this;
    }

    public ReconhecimentoRequestDTOBuilder comTime(String timeEsperado) {
        this.time = timeEsperado;
        return this;
    }

    public ReconhecimentoRequestDTOBuilder comIp(String ipEsperado) {
        this.ip = ipEsperado;
        return this;
    }

    public ReconhecimentoRequestDTOBuilder comType(String typeEsperado) {
        this.type = typeEsperado;
        return this;
    }

    public ReconhecimentoRequestDTOBuilder comPath(String pathEsperado) {
        this.path = pathEsperado;
        return this;
    }

    public ReconhecimentoRequestDTO construir() throws Exception {
        return new ReconhecimentoRequestDTO(deviceKey, personId, time, ip, type, path);
    }

}
