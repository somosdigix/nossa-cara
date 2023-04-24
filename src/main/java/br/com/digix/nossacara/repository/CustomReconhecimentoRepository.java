package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Reconhecimento;

import java.time.LocalDate;
import java.util.List;

public interface CustomReconhecimentoRepository {
    int quantidadeDeReconhecimentosDistintos(LocalDate dia, List<String> numeroDispositivo);

    Reconhecimento findAllByDataDeCriacaoAndPersonIdAndDeviceKey(LocalDate dia, List<String> numeroDispositivo, String personId);
}
