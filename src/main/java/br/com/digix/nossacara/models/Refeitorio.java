package br.com.digix.nossacara.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Refeitorio {
    private String numeroDispositivo;
    private String nome;
}
