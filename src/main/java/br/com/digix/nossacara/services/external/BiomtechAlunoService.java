package br.com.digix.nossacara.services.external;

import br.com.digix.nossacara.dtos.external.BiomtechAlunoDTO;
import br.com.digix.nossacara.dtos.external.BiomtechAlunosDTO;
import br.com.digix.nossacara.dtos.external.BiomtechAuthDTO;
import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.repository.AlunoRepository;
import br.com.digix.nossacara.repository.EscolaRepository;
import br.com.digix.nossacara.schedule.AgendamentoAtualizacaoAlunos;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BiomtechAlunoService {

    @Value("${biomtech.baseURL:https://localhost:8080}")
    private String baseURL;
    @Value("${biomtech.userId}")
    private String userId;
    private String URL_GET = "/api/Student?pageSize=1000&currentPage=1";
    private final BiomtechAuthService biomtechAuthService;
    private final RestTemplate restTemplate;

    private final AlunoRepository alunoRepository;

    private final EscolaRepository escolaRepository;

    private static final Logger log = LoggerFactory.getLogger(AgendamentoAtualizacaoAlunos.class);

    @Transactional
    public void atualizarBaseDeAlunos(Escola escola, String usuario, String senha) {
        BiomtechAuthDTO biomtechAuthDTO = biomtechAuthService.autenticar(usuario, senha);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Selected-User-Profile-Id", userId);
        headers.set("Authorization", "Bearer " + biomtechAuthDTO.getUserAccessToken());
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<BiomtechAlunosDTO> alunosDTOResponseEntity = restTemplate.exchange(baseURL + URL_GET, HttpMethod.GET, requestEntity, BiomtechAlunosDTO.class);
        BiomtechAlunosDTO biomtechAlunosDTO = alunosDTOResponseEntity.getBody();

        escola.setQuantidadeAlunos(Long.valueOf(Objects.requireNonNull(biomtechAlunosDTO).getPageInfoDTO().getTotal()).intValue());
        escolaRepository.save(escola);

        biomtechAlunosDTO.getBiomtechAlunoDTO().forEach(alunoDTO -> this.atualizarAlunos(alunoDTO, escola));
        log.info("Foram atualizados {} alunos", escola.getQuantidadeAlunos());
    }

    private void atualizarAlunos(BiomtechAlunoDTO biomtechAlunoDTO, Escola escola) {
        Optional<Aluno> optionalAluno = alunoRepository.findFirstByPersonId(biomtechAlunoDTO.getPersonId());
        Aluno aluno;
        if(optionalAluno.isPresent()) {
            aluno = optionalAluno.get();
            aluno.setEscola(escola);
            aluno.setPersonId(biomtechAlunoDTO.getPersonId());
            aluno.setNome(biomtechAlunoDTO.getNome());
            aluno.setTurma(biomtechAlunoDTO.getBiomtechTurmaDTO().get(0).getTurma());
            aluno.setTurno(biomtechAlunoDTO.getBiomtechTurmaDTO().get(0).getTurno());
            aluno.setEtapaDeEnsino(biomtechAlunoDTO.getBiomtechTurmaDTO().get(0).getEtapaDeEnsino());
        } else {
            aluno = Aluno.builder()
                    .escola(escola)
                    .personId(biomtechAlunoDTO.getPersonId())
                    .nome(biomtechAlunoDTO.getNome())
                    .turma(biomtechAlunoDTO.getBiomtechTurmaDTO().get(0).getTurma())
                    .turno(biomtechAlunoDTO.getBiomtechTurmaDTO().get(0).getTurno())
                    .etapaDeEnsino(biomtechAlunoDTO.getBiomtechTurmaDTO().get(0).getEtapaDeEnsino())
                    .build();
        }
        alunoRepository.save(aluno);
    }
}
