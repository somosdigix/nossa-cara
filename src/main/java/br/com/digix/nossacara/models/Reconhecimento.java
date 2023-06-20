package br.com.digix.nossacara.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reconhecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceKey;
    private String personId;
    private LocalDateTime dataDeCriacao;
    private String time;
    private String ip;
    private String type;
    private String path;

    public Reconhecimento(String deviceKey, String personId, LocalDateTime dataDeCriacao, String time, String ip, String type, String path) {
        this.deviceKey = deviceKey;
        this.personId = personId;
        this.dataDeCriacao = dataDeCriacao;
        this.time = time;
        this.ip = ip;
        this.type = type;
        this.path = path;
    }
}