package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Reconhecimento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReconhecimentoRepositoryImpl implements CustomReconhecimentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int quantidadeDeReconhecimentosDistintos(LocalDate dia, List<String> numeroDispositivo) {
        LocalDateTime diaInicio = dia.atStartOfDay();
        LocalDateTime diaFim = dia.plusDays(1).atStartOfDay();
        Query singleResult = entityManager.createQuery(
                        "select count(DISTINCT r.personId) from Reconhecimento r " +
                                "where r.dataDeCriacao >= :diaInicio and r.dataDeCriacao <= :diaFim and r.deviceKey in (:numeroDispositivo)")
                .setParameter("diaInicio", diaInicio)
                .setParameter("diaFim", diaFim)
                .setParameter("numeroDispositivo", numeroDispositivo);
        Object obj = singleResult.getSingleResult();
        return obj != null ? Integer.parseInt(obj.toString()) : 0;
    }

    @Override
    public List<Reconhecimento> findAllByDataDeCriacaoAndPersonIdAndDeviceKey(LocalDate dia, List<String> numeroDispositivo, String personId) {
        LocalDateTime diaInicio = dia.atStartOfDay();
        LocalDateTime diaFim = dia.plusDays(1).atStartOfDay();
        Query query = entityManager.createQuery(
                        "select r from Reconhecimento r " +
                                "where r.dataDeCriacao >= :diaInicio and r.dataDeCriacao <= :diaFim " +
                                "and r.deviceKey in (:numeroDispositivo) " +
                                "and r.personId = :personId")
                .setParameter("diaInicio", diaInicio)
                .setParameter("diaFim", diaFim)
                .setParameter("numeroDispositivo", numeroDispositivo)
                .setParameter("personId", personId);
        return (List<Reconhecimento>) query.getResultList();
    }
    
}