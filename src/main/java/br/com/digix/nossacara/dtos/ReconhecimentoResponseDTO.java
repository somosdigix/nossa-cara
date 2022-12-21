package br.com.digix.nossacara.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ReconhecimentoResponseDTO {
    private Long id;
    private String deviceKey;
    private Long personId;
    private Long time;
    private String ip;
    private String type;
    private String path;
}
