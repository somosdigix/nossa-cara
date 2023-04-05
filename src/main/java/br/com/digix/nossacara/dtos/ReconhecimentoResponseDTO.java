package br.com.digix.nossacara.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReconhecimentoResponseDTO {
    private Long id;
    private String deviceKey;
    private String personId;
    private LocalDateTime dataDeCriacao;
    private String ip;
    private String type;
    private String path;
}
