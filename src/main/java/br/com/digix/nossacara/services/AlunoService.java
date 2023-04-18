package br.com.digix.nossacara.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.AlunoPresenteResponseDTO;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;

@Service
public class AlunoService {

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    public List<AlunoPresenteResponseDTO> criarListaAlunosPresentes(LocalDate data) {

        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.atTime(23, 59, 59);

        List<String> personIdsDoDia = reconhecimentoRepository.findAllPersonIdByDataDeCriacao(inicioDoDia, fimDoDia);

        List<AlunoPresenteResponseDTO> alunosPresentes = new ArrayList<>();

        for (String personId : personIdsDoDia) {
            String nomeAluno = buscarNomeAlunoPorPersonId(personId);
            AlunoPresenteResponseDTO alunoPresente = new AlunoPresenteResponseDTO(nomeAluno, personId);
            alunosPresentes.add(alunoPresente);
        }

        return alunosPresentes;
    }

    private String buscarNomeAlunoPorPersonId(String personId) {

        Reconhecimento reconhecimento = reconhecimentoRepository.findFirstByPersonIdOrderByIdDesc(personId);

        if (reconhecimento != null) {

            return reconhecimento.getPersonId();
        } else {

            return "Aluno não encontrado";
        }
    }
}
