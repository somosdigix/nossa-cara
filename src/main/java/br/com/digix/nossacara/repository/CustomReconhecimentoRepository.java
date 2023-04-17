package br.com.digix.nossacara.repository;

import java.time.LocalDate;
import java.util.List;

public interface CustomReconhecimentoRepository {
    int quantidadeDeReconhecimentosDistintos(LocalDate dia, List<String> numeroDispositivo);
}
