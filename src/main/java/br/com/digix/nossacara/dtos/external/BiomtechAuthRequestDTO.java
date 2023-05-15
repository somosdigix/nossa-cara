package br.com.digix.nossacara.dtos.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BiomtechAuthRequestDTO {
    private String login;
    private String password;
}
