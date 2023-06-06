package br.com.digix.nossacara.services.external;

import br.com.digix.nossacara.dtos.external.BiomtechAuthDTO;
import br.com.digix.nossacara.dtos.external.BiomtechAuthRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class BiomtechAuthServiceTest {

    @InjectMocks
    private BiomtechAuthService biomtechAuthService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void deve_autenticar() {
        String usuario = "teste";
        String senha = "teste";
        String baseUrlFake = "https://localhost:8080";
        String urlFake = baseUrlFake + "/api/Authentication";
        ResponseEntity<BiomtechAuthDTO> retornoFake = new ResponseEntity<>(BiomtechAuthDTO.builder().build(), HttpStatus.OK);
        ReflectionTestUtils.setField(biomtechAuthService, "baseURL", baseUrlFake);
        BiomtechAuthRequestDTO requisicaoFake = BiomtechAuthRequestDTO.builder().login(usuario).password(senha).build();
        Mockito.when(restTemplate.postForEntity(urlFake, requisicaoFake, BiomtechAuthDTO.class)).thenReturn(retornoFake);

        BiomtechAuthDTO biomtechAuthDTO = biomtechAuthService.autenticar("teste", senha);

        Assertions.assertThat(biomtechAuthDTO)
                .isNotNull()
                .isEqualTo(retornoFake.getBody());
    }
}