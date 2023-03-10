package br.com.digix.nossacara.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Reconhecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceKey;
    private String personId;
    private Long time;
    private String ip;
    private String type;
    private String path;  
    
    public Reconhecimento(String deviceKey, String personId, Long time, String ip, String type, String path) {
        this.deviceKey = deviceKey;
        this.personId = personId;
        this.time = time;
        this.ip = ip;
        this.type = type;
        this.path = path;
    }
}
