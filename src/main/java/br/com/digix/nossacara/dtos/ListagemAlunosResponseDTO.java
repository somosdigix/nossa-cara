package br.com.digix.nossacara.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListagemAlunosResponseDTO {
    private PageInfoDTO pageInfo;
    private List<AlunoPresenteResponseDTO> alunos;
}
