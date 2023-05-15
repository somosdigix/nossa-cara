package br.com.digix.nossacara.dtos.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BiomtechAuthDTO {
    private String userAccessToken;
    private String expiration;
    private List<PerfilBiomtechAuthDTO> profiles;
}
