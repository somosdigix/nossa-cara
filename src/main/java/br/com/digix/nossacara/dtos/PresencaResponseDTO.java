package br.com.digix.nossacara.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PresencaResponseDTO {
    private int quantidadeEntradaEscola;
    private int quantidadeAusenteEscola;
    private int quantidadeEntradaRefeitorio;
    private int quantidadeAusenteRefeitorio;
}
