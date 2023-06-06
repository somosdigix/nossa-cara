package br.com.digix.nossacara.dtos.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiomtechTurmaDTO {
    @JsonProperty("name")
    private String turma;

    @JsonProperty("shift")
    private String turno;

    @JsonProperty("gradeLevel")
    private String etapaDeEnsino;
}
