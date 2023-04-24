package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Reconhecimento;

import java.time.LocalDate;

public interface CustomReconhecimentoRepository {
    int quantidadeDeReconhecimentosDistintos(LocalDate dia, String numeroDispositivo);

    Reconhecimento findAllByDataDeCriacaoAndPersonIdAndDeviceKey(LocalDate dia, String numeroDispositivo, String personId);
}
