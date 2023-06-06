package br.com.digix.nossacara.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoPresenteResponseDTO {
    private String nome;
    private String estapaDeEnsino;
    private String turma;
    private String turno;
    private String horarioEntrada;
    private List<String> horariosRefeitorio;
}
