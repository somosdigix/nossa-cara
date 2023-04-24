package br.com.digix.nossacara.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlunoPresenteResponseDTO {
    private String nome;
    private String personId;
    LocalDateTime horarioDeEntradaEscola;
    List<LocalDateTime> horariosDeEntradaRefeitorio;
}
