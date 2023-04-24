package br.com.digix.nossacara.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

import br.com.digix.nossacara.models.Reconhecimento;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlunoPresenteResponseDTO {
    String horarioDeEntradaEscola = new Reconhecimento().getDataDeCriacao().toString(); 
    // LocalDateTime horarioDeEntradaEscola;
    List<LocalDateTime> horarioDeEntradaRefeitorio;
}
