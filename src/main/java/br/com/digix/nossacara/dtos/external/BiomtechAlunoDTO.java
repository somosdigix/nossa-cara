package br.com.digix.nossacara.dtos.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiomtechAlunoDTO {
    @JsonProperty("fullName")
    private String nome;

    @JsonProperty("originId")
    private String personId;

    @JsonProperty("classroom")
    private List<BiomtechTurmaDTO> biomtechTurmaDTO;

}
