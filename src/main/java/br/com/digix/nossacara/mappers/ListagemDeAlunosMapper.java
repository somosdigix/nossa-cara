package br.com.digix.nossacara.mappers;

import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.models.Aluno;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ListagemDeAlunosMapper {
    ListagemAlunosResponseDTO from(Page<Aluno> alunoPage);
}
