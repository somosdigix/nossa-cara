package br.com.digix.nossacara.services.external;

import br.com.digix.nossacara.dtos.external.BiomtechAuthDTO;
import br.com.digix.nossacara.dtos.external.BiomtechAuthRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BiomtechAuthService {

    @Value("${biomtech.baseURL:https://localhost:8080}")
    private String baseURL;

    private String URL_AUTH = "/api/Authentication";

    private final RestTemplate restTemplate;

    public BiomtechAuthDTO autenticar(String usuario, String senha) {
        BiomtechAuthRequestDTO request = BiomtechAuthRequestDTO.builder().login(usuario).password(senha).build();
        ResponseEntity<BiomtechAuthDTO> biomtechAuthDTOResponseEntity = restTemplate.postForEntity(baseURL + URL_AUTH, request, BiomtechAuthDTO.class);
        return biomtechAuthDTOResponseEntity.getBody();
    }
}
