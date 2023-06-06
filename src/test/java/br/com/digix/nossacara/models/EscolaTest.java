package br.com.digix.nossacara.models;

import br.com.digix.nossacara.repository.EscolaRepository;
import br.com.digix.nossacara.repository.LocalDeEntradaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class EscolaTest {

    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    @Test
    void deve_ter_um_local_de_entrada() {
        LocalDeEntrada localDeEntradaEsperado = LocalDeEntrada.builder().nome("Entrada Principal").numeroDispositivo("84E0F42117E8607A").build();
        localDeEntradaRepository.save(localDeEntradaEsperado);

        Escola escola = Escola.builder().quantidadeAlunos(15).nome("EE Lucia Martins Coelho").locaisDeEntrada(Collections.singletonList(localDeEntradaEsperado)).build();
        escolaRepository.save(escola);

        Assertions.assertThat(escola.getLocaisDeEntrada()).containsExactly(localDeEntradaEsperado);
    }

    @Test
    void deve_ter_mais_de_um_local_de_entrada() {
        LocalDeEntrada localDeEntradaPrincipal = LocalDeEntrada.builder().nome("Entrada Principal").numeroDispositivo("84E0F42117E8607A").build();
        LocalDeEntrada localDeEntradaLateral = LocalDeEntrada.builder().nome("Entrada Lateral").numeroDispositivo("84E0F4210CE3607A").build();
        List<LocalDeEntrada> locaisDeEntrada = Arrays.asList(localDeEntradaPrincipal, localDeEntradaLateral);
        localDeEntradaRepository.saveAll(locaisDeEntrada);

        Escola escola = Escola.builder().quantidadeAlunos(15).nome("EE Lucia Martins Coelho").locaisDeEntrada(locaisDeEntrada).build();
        escolaRepository.save(escola);

        Assertions.assertThat(escola.getLocaisDeEntrada()).containsExactly(localDeEntradaPrincipal, localDeEntradaLateral).hasSize(2);
    }
}
