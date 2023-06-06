package br.com.digix.nossacara.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.digix.nossacara.dtos.AlunoPresenteResponseDTO;
import br.com.digix.nossacara.models.Aluno;

public class AlunoPresenteMapper {
    public List<AlunoPresenteResponseDTO> retornaListagemAlunosPresentes(Page<Aluno> alunoPage) {
        return alunoPage.stream().map(this::criarAlunoPresente).collect(Collectors.toList());
    }

    private AlunoPresenteResponseDTO criarAlunoPresente(Aluno a) {
        return AlunoPresenteResponseDTO.builder().estapaDeEnsino(a.getEtapaDeEnsino().getNome()).nome(a.getNome()).turma(a.getTurma()).turno(a.getTurno()).build();
    }

}
