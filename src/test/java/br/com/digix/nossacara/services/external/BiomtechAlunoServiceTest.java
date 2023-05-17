package br.com.digix.nossacara.services.external;

import br.com.digix.nossacara.dtos.PageInfoDTO;
import br.com.digix.nossacara.dtos.external.BiomtechAlunoDTO;
import br.com.digix.nossacara.dtos.external.BiomtechAlunosDTO;
import br.com.digix.nossacara.dtos.external.BiomtechAuthDTO;
import br.com.digix.nossacara.dtos.external.BiomtechTurmaDTO;
import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.repository.AlunoRepository;
import br.com.digix.nossacara.repository.EscolaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BiomtechAlunoServiceTest {

    @InjectMocks
    private BiomtechAlunoService biomtechAlunoService;
    @Mock
    private AlunoRepository alunoRepository;
    @Mock
    private EscolaRepository escolaRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private BiomtechAuthService biomtechAuthService;
    private final String URL_GET = "/api/Student?pageSize=1000&currentPage=1";

    @Test
    void atualizarBaseDeAlunos() {
        String usuario = "teste";
        String senha = "teste";
        String baseUrlFake = "https://localhost:8080";
        String userId = "34234ffsd";
        Escola escola = Escola.builder().id(11).build();
        List<Aluno> alunosEsperados = cadastrarAlunos(Arrays.asList("Enzo", "Fl�vio"),
                escola);

        ReflectionTestUtils.setField(biomtechAuthService, "baseURL", baseUrlFake);
        ReflectionTestUtils.setField(biomtechAlunoService, "baseURL", baseUrlFake);
        ReflectionTestUtils.setField(biomtechAlunoService, "userId", userId);

        BiomtechAuthDTO biomtechAuthDTO = BiomtechAuthDTO.builder().build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Selected-User-Profile-Id", userId);
        headers.set("Authorization", "Bearer " + biomtechAuthDTO.getUserAccessToken());
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<BiomtechAlunosDTO> retornoFake = new ResponseEntity<>(this.construirBiomtechAlunosDTO(alunosEsperados), HttpStatus.OK);

        when(biomtechAuthService.autenticar(usuario, senha)).thenReturn(biomtechAuthDTO);
        when(restTemplate.exchange(baseUrlFake + URL_GET, HttpMethod.GET, requestEntity, BiomtechAlunosDTO.class)).thenReturn(retornoFake);
        when(alunoRepository.findFirstByPersonId("1")).thenReturn(Optional.of(alunosEsperados.get(0)));
        when(alunoRepository.findFirstByPersonId("2")).thenReturn(Optional.of(alunosEsperados.get(1)));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(alunosEsperados.get(0));

        biomtechAlunoService.atualizarBaseDeAlunos(escola, usuario, senha);

        verify(alunoRepository, times(2)).save(any(Aluno.class));

    }

    private BiomtechAlunosDTO construirBiomtechAlunosDTO(List<Aluno> alunosEsperados) {
        return BiomtechAlunosDTO.builder()
                .pageInfoDTO(PageInfoDTO.builder()
                        .total(2)
                        .build())
                .biomtechAlunoDTO(alunosEsperados.stream().map(this::toBiomtechAlunoDTO).collect(Collectors.toList()))
                .build();
    }

    private BiomtechAlunoDTO toBiomtechAlunoDTO(Aluno aluno) {
        return BiomtechAlunoDTO.builder()
                .biomtechTurmaDTO(Collections.singletonList(BiomtechTurmaDTO.builder().turma(aluno.getTurma()).build()))
                .nome(aluno.getNome())
                .personId(aluno.getPersonId())
                .build();
    }

    private List<Aluno> cadastrarAlunos(List<String> nomes, Escola escola) {
        int contadorParaPersonId = 1;
        List<Aluno> alunosEsperados = new ArrayList<>();
        for (String nome : nomes) {
            Aluno aluno = Aluno.builder().nome(nome).etapaDeEnsino("Ensino medio").turma("Starttech").turno("Vespertino")
                    .personId(Integer.toString(contadorParaPersonId)).escola(escola).build();
            alunosEsperados.add(aluno);
            contadorParaPersonId++;
        }
        alunosEsperados.sort(Comparator.comparing(Aluno::getNome));
        return alunosEsperados;
    }
}