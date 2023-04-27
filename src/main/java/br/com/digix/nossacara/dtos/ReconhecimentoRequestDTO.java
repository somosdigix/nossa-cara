package br.com.digix.nossacara.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ReconhecimentoRequestDTO {
    private String deviceKey;
    private String personId;
    private String time;
    private String ip;
    private String type;
    private String path; 
}