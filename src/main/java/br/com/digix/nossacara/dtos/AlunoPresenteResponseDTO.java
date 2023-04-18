package br.com.digix.nossacara.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlunoPresenteResponseDTO {

    private String nome;
    private String personId;
}
