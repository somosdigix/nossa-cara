package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoRepositoryImpl implements CustomAlunoRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Aluno> buscarAlunosComReconhecimentoNoDia(Escola escola, LocalDate dia, Pageable pageable) {
        LocalDateTime diaInicio = dia.atStartOfDay();
        LocalDateTime diaFim = dia.plusDays(1).atStartOfDay();
        List<String> locaisDeEntrada = escola.getLocaisDeEntrada().stream().map(LocalDeEntrada::getNumeroDispositivo).collect(Collectors.toList());
        int pageSize = pageable.getPageSize();
        Query queryAlunos = entityManager.createQuery(
                        "select a from Aluno a " +
                                "where a.personId in " +
                                "(select r.personId from Reconhecimento r " +
                                "where r.dataDeCriacao between :diaInicio " +
                                "and :diaFim " +
                                "and r.deviceKey in (:locaisDeEntrada)) " +
                                "order by a.nome asc")
                .setParameter("diaInicio", diaInicio)
                .setParameter("diaFim", diaFim)
                .setParameter("locaisDeEntrada", locaisDeEntrada)
                .setMaxResults(pageSize)
                .setFirstResult((pageable.getPageNumber()-1) * pageSize);
        return (List<Aluno>) queryAlunos.getResultList();
    }

    
}
