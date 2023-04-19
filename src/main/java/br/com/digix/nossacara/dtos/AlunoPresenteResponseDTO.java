package br.com.digix.nossacara.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlunoPresenteResponseDTO {
    LocalDateTime horarioDeEntradaEscola;
    LocalDateTime horarioDeEntradaRefeitorio;
}
