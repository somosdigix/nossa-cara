package br.com.digix.nossacara.repository;

import java.time.LocalDate;

public interface CustomReconhecimentoRepository {
    int quantidadeDeReconhecimentosDistintos(LocalDate dia, String numeroDispositivo);
}
