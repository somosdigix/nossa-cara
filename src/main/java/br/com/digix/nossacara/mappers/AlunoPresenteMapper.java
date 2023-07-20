package br.com.digix.nossacara.mappers;

import br.com.digix.nossacara.dtos.AlunoPresenteResponseDTO;
import br.com.digix.nossacara.models.Aluno;
import org.springframework.data.domain.Page;

import java.util.List;

public class AlunoPresenteMapper {
    public List<AlunoPresenteResponseDTO> retornaListagemAlunosPresentes(Page<Aluno> alunoPage) {
        return alunoPage.stream().map(this::criarAlunoPresente).toList();
    }

    private AlunoPresenteResponseDTO criarAlunoPresente(Aluno a) {
        return AlunoPresenteResponseDTO
                .builder()
                    .etapaDeEnsino(a.getEtapaDeEnsino().getNome())
                    .nome(a.getNome())
                    .turma(a.getTurma())
                    .turno(a.getTurno())
                    .personId(a.getPersonId())
                .build();
    }

}
