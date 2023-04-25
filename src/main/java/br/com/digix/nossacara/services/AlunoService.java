package br.com.digix.nossacara.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Refeitorio;
import br.com.digix.nossacara.repository.LocalDeEntradaRepository;
import br.com.digix.nossacara.repository.RefeitorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digix.nossacara.dtos.AlunoPresenteResponseDTO;
import br.com.digix.nossacara.models.Reconhecimento;
import br.com.digix.nossacara.repository.ReconhecimentoRepository;

@Service
public class AlunoService {

    @Autowired
    private ReconhecimentoRepository reconhecimentoRepository;

    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;

    @Autowired
    private RefeitorioRepository refeitorioRepository;

    // public List<AlunoPresenteResponseDTO> criarListaAlunosPresentes(LocalDate data, Escola escola) {

    //     LocalDateTime inicioDoDia = data.atStartOfDay();
    //     LocalDateTime fimDoDia = data.atTime(23, 59, 59);

    //     List<String> personIdsDoDia = reconhecimentoRepository.findAllPersonIdByDataDeCriacao(inicioDoDia, fimDoDia);

    //     List<AlunoPresenteResponseDTO> alunosPresentes = new ArrayList<>();

    //     for (String personId : personIdsDoDia) {
    //         String nomeAluno = buscarNomeAlunoPorPersonId(personId);
    //         Collection<Refeitorio> refeitorios = escola.getRefeitorios();
    //         Reconhecimento reconhecimentosRefeitorios = reconhecimentoRepository.findAllByDataDeCriacaoAndPersonIdAndDeviceKey(data, );
    //         Collection<LocalDeEntrada> locaisDeEntrada = escola.getLocaisDeEntrada();
    //         Reconhecimento reconhecimentosLocaisDeEntrada = reconhecimentoRepository.findAllByDataDeCriacaoAndPersonIdAndDeviceKey(data, )
    //         AlunoPresenteResponseDTO alunoPresente = new AlunoPresenteResponseDTO(nomeAluno, personId, reconhecimentosLocaisDeEntrada.getDataDeCriacao(), horariosDeEntradaRefeitorio);
    //         alunosPresentes.add(alunoPresente);
    //     }

    //     return alunosPresentes;
    // }

    private String buscarNomeAlunoPorPersonId(String personId) {

        Reconhecimento reconhecimento = reconhecimentoRepository.findFirstByPersonIdOrderByIdDesc(personId);

        if (reconhecimento != null) {

            return reconhecimento.getPersonId();
        } else {

            return "Aluno não encontrado";
        }
    }
}
