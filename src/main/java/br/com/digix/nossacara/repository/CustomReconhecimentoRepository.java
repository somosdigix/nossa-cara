package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Reconhecimento;

import java.time.LocalDate;
import java.util.List;

public interface CustomReconhecimentoRepository {
    int quantidadeDeReconhecimentosDistintos(LocalDate dia, List<String> numeroDispositivo);

    int quantidadeDeAlunosPresentesNoRefeitorio(LocalDate dia, List<String> dispositivosRefeitorio);

    int quantidadeDeAlunosPresentesNaEscola(LocalDate dia, List<String> dispositivosEntrada);

    int quantidadeDeReconhecimentosDistintos(LocalDate dia, List<String> numeroDispositivo, String nome, long etapaDeEnsino);

    List<Reconhecimento> findAllByDataDeCriacaoAndPersonIdAndDeviceKey(LocalDate dia, List<String> numeroDispositivo, String personId);

    int quantidadeDeAusenciasDistintas(LocalDate data, List<String> toList, String nomeAluno, long etapaDeEnsinoId);
}
