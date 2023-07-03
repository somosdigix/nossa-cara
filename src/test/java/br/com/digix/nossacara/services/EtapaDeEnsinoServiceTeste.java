package br.com.digix.nossacara.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.mappers.EtapaDeEnsinoMapper;
import br.com.digix.nossacara.models.EtapaDeEnsino;
import br.com.digix.nossacara.repository.EtapaDeEnsinoRepository;

@SpringBootTest
public class EtapaDeEnsinoServiceTeste {
    @Autowired
    private EtapaDeEnsinoRepository etapaDeEnsinoRepository;

    @Autowired
    private EtapaDeEnsinoMapper etapaDeEnsinoMapper;

    @Autowired
    private EtapaDeEnsinoService etapaDeEnsinoService;

    @BeforeEach
    void setUp() {
        etapaDeEnsinoRepository.deleteAll();
    }

    @Test
    void deve_buscar_uma_etapa_de_ensino_pelo_id() {
        EtapaDeEnsino etapaDeEnsinoCriada = EtapaDeEnsino.builder().nome("Fundamental").build();
        etapaDeEnsinoRepository.save(etapaDeEnsinoCriada);
        EtapaDeEnsinoResponseDTO etapaDeEnsinoDevolvido = etapaDeEnsinoService.buscarPorId(etapaDeEnsinoCriada.getId());
        assertEquals(etapaDeEnsinoCriada.getNome(), etapaDeEnsinoDevolvido.getNome());
    }

    @Test
    void deve_devolver_todas_etapas_de_ensino_criadas() {
        EtapaDeEnsino etapaDeEnsinoCriada1 = EtapaDeEnsino.builder().nome("Fundamental").build();
        EtapaDeEnsino etapaDeEnsinoCriada2 = EtapaDeEnsino.builder().nome("Médio").build();
        etapaDeEnsinoRepository.save(etapaDeEnsinoCriada1);
        etapaDeEnsinoRepository.save(etapaDeEnsinoCriada2);
        List<EtapaDeEnsinoResponseDTO> etapaDeEnsinoRetornadas = etapaDeEnsinoService.buscarTodos();
        assertEquals(etapaDeEnsinoRetornadas.size(), 2);
    }
}
