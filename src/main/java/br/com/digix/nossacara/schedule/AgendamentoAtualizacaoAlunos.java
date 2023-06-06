package br.com.digix.nossacara.schedule;

import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.repository.EscolaRepository;
import br.com.digix.nossacara.services.external.BiomtechAlunoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendamentoAtualizacaoAlunos {

    @Value("${biomtech.login}")
    private String usuario;
    @Value("${biomtech.password}")
    private String senha;

    private final BiomtechAlunoService biomtechAlunoService;
    private final EscolaRepository escolaRepository;
    private static final Logger log = LoggerFactory.getLogger(AgendamentoAtualizacaoAlunos.class);

    @Scheduled(cron = "0 0 1 * * *")
    public void atualizarAlunosDiariamente() {
        log.info("Iniciando atualizacao de alunos");
        Escola escola = escolaRepository.findAll().get(0);
        biomtechAlunoService.atualizarBaseDeAlunos(escola, usuario, senha);
    }
}
