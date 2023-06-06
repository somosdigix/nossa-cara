package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Escola;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EscolaRepositoryTest {
    @Autowired
    private EscolaRepository escolaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;
    @Autowired
    private RefeitorioRepository refeitorioRepository;

    @BeforeEach
    @AfterEach
    void setUp() {
        alunoRepository.deleteAll();
        localDeEntradaRepository.deleteAll();
        refeitorioRepository.deleteAll();
        escolaRepository.deleteAll();
    }

    @Test
    public void deve_salvar_o_nome_de_uma_escola() {
        // Arrange
        Escola escola = Escola.builder().nome("E E Lucia Martins Coelho").quantidadeAlunos(485).build();

        // Action
        escolaRepository.save(escola);

        // Asserts
        assertThat(escola.getId()).isNotNull();
    }

    @Test
    @Transactional
    public void deve_buscar_uma_escola_pelo_nome() {
        // Arrange
        String nomeEsperado = "E E Lucia Martins Coelho";
        Escola escolaEsperada = Escola.builder().nome(nomeEsperado).build();

        // Action
        escolaRepository.save(escolaEsperada);
        List<Escola> escolasRetornadas = escolaRepository.findByNomeContaining(nomeEsperado);

        // Asserts
        assertThat(escolasRetornadas).hasSize(1).containsExactly(escolaEsperada);
        
    }

}
