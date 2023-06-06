package br.com.digix.nossacara.mappers;

import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.dtos.PageInfoDTO;
import br.com.digix.nossacara.models.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ListagemDeAlunosMapperImpl implements ListagemDeAlunosMapper {
    @Override
    public ListagemAlunosResponseDTO from(Page<Aluno> alunoPage) {
        return ListagemAlunosResponseDTO.builder()
                .pageInfo(
                        PageInfoDTO.builder()
                                .total(alunoPage.getTotalElements())
                                .pageSize(alunoPage.getSize())
                                .hasPrevious(alunoPage.hasPrevious())
                                .hasNext(alunoPage.hasNext())
                                .totalPages(alunoPage.getTotalPages())
                                .currentPage(alunoPage.getNumber() + 1)
                                .build())
                .alunos(new AlunoPresenteMapper().retornaListagemAlunosPresentes(alunoPage))
                .build();
    }
}
