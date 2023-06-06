package br.com.digix.nossacara.dtos.external;

import br.com.digix.nossacara.dtos.PageInfoDTO;
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
public class BiomtechAlunosDTO {

    @JsonProperty("pageInfo")
    private PageInfoDTO pageInfoDTO;
    @JsonProperty("items")
    private List<BiomtechAlunoDTO> biomtechAlunoDTO;
}
