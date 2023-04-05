package br.com.digix.nossacara.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ReconhecimentoSucessResponseDTO {
    private boolean success;
    private int result;
}
