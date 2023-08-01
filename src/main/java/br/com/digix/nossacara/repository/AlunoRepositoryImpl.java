package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Aluno;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Refeitorio;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepositoryImpl implements CustomAlunoRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Aluno> buscarAlunosComReconhecimentoNoDia(Escola escola, String nomeAluno, long etapaDeEnsinoId,
            LocalDate dia, Pageable pageable) {
        LocalDateTime diaInicio = dia.atStartOfDay();
        LocalDateTime diaFim = dia.plusDays(1).atStartOfDay();
        List<String> todosDispositivos = new ArrayList<>();
        todosDispositivos.addAll(escola.getLocaisDeEntrada().stream()
                .map(LocalDeEntrada::getNumeroDispositivo)
                .toList());

        todosDispositivos.addAll(escola.getRefeitorios().stream()
                .map(Refeitorio::getNumeroDispositivo)
                .toList());
        int pageSize = pageable.getPageSize();
        int currentPage = (pageable.getPageNumber() - 1);
        var queryAlunos = entityManager.createQuery(
                        "select a from Aluno a " +
                                "where a.personId in " +
                                "(select r.personId from Reconhecimento r " +
                                "where r.dataDeCriacao between :diaInicio " +
                                "and :diaFim " +
                                (StringUtils.isNotBlank(nomeAluno) ? "and a.nome like :nome " : "") +
                                (etapaDeEnsinoId > 0 ? "and a.etapaDeEnsino.id = :etapaDeEnsinoId " : "") +
                                "and r.deviceKey in (:locaisDeEntrada)) " +
                                "order by a.nome asc")
                .setParameter("diaInicio", diaInicio)
                .setParameter("diaFim", diaFim)
                .setParameter("locaisDeEntrada", todosDispositivos)
                .setMaxResults(pageSize)
                .setFirstResult(currentPage * pageSize);
        if (StringUtils.isNotBlank(nomeAluno)) {
            queryAlunos.setParameter("nome", "%" + nomeAluno + "%");
        }
        if (etapaDeEnsinoId > 0) {
            queryAlunos.setParameter("etapaDeEnsinoId", etapaDeEnsinoId);
        }
        List<Aluno> alunos = queryAlunos.getResultList();
        return new PageImpl<>(alunos, PageRequest.of(currentPage, pageSize), escola.getQuantidadeAlunos());
    }

    @Override
    public Page<Aluno> buscarAlunosComReconhecimentoNoDiaNoRefeitorio(Escola escola, String nomeAluno, long etapaDeEnsinoId, LocalDate dia, Pageable pageable) {
        LocalDateTime diaInicio = dia.atStartOfDay();
        LocalDateTime diaFim = dia.plusDays(1).atStartOfDay();
        List<String> refeitorios = escola.getRefeitorios().stream().map(Refeitorio::getNumeroDispositivo).toList();
        int pageSize = pageable.getPageSize();
        int currentPage = (pageable.getPageNumber()-1);
        var queryAlunos = entityManager.createQuery(
                        "select a from Aluno a " +
                                "where a.personId in " +
                                "(select r.personId from Reconhecimento r " +
                                "where r.dataDeCriacao between :diaInicio " +
                                "and :diaFim " +
                                (StringUtils.isNotBlank(nomeAluno) ? "and a.nome like :nome " : "") +
                                (etapaDeEnsinoId > 0 ? "and a.etapaDeEnsino.id = :etapaDeEnsinoId " : "") +
                                "and r.deviceKey in (:refeitorios)) " +
                                "order by a.nome asc")
                .setParameter("diaInicio", diaInicio)
                .setParameter("diaFim", diaFim)
                .setParameter("refeitorios", refeitorios)
                .setMaxResults(pageSize)
                .setFirstResult(currentPage * pageSize);
        if (StringUtils.isNotBlank(nomeAluno)) {
            queryAlunos.setParameter("nome", "%" + nomeAluno + "%");
        }
        if (etapaDeEnsinoId > 0) {
            queryAlunos.setParameter("etapaDeEnsinoId", etapaDeEnsinoId);
        }
        List<Aluno> alunos = queryAlunos.getResultList();
        return new PageImpl<>(alunos, PageRequest.of(currentPage, pageSize), escola.getQuantidadeAlunos());
    }

    @Override
    public Page<Aluno> buscarAlunosAusentesNaEntrada(Escola escola, String nomeAluno, long etapaDeEnsinoId, LocalDate dia, Pageable pageable) {
        LocalDateTime diaInicio = dia.atStartOfDay();
        LocalDateTime diaFim = dia.plusDays(1).atStartOfDay();
        List<String> todosDispositivos = new ArrayList<>();
        todosDispositivos.addAll(escola.getLocaisDeEntrada().stream()
                .map(LocalDeEntrada::getNumeroDispositivo)
                .toList());

        todosDispositivos.addAll(escola.getRefeitorios().stream()
                .map(Refeitorio::getNumeroDispositivo)
                .toList());
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber() - 1;
        var queryAlunos = entityManager.createQuery(
                        "SELECT a FROM Aluno a " +
                                "WHERE a.personId NOT IN " +
                                "(SELECT r.personId FROM Reconhecimento r " +
                                "WHERE r.dataDeCriacao BETWEEN :diaInicio " +
                                "AND :diaFim " +
                                "AND r.deviceKey IN (:todosDispositivos)) " +
                                (StringUtils.isNotBlank(nomeAluno) ? "AND a.nome LIKE :nome " : "") +
                                (etapaDeEnsinoId > 0 ? "AND a.etapaDeEnsino.id = :etapaDeEnsinoId " : "") +
                                "ORDER BY a.nome ASC")
                .setParameter("diaInicio", diaInicio)
                .setParameter("diaFim", diaFim)
                .setParameter("todosDispositivos", todosDispositivos)
                .setMaxResults(pageSize)
                .setFirstResult(currentPage * pageSize);
        if (StringUtils.isNotBlank(nomeAluno)) {
            queryAlunos.setParameter("nome", "%" + nomeAluno + "%");
        }
        if (etapaDeEnsinoId > 0) {
            queryAlunos.setParameter("etapaDeEnsinoId", etapaDeEnsinoId);
        }
        List<Aluno> alunos = queryAlunos.getResultList();
        return new PageImpl<>(alunos, PageRequest.of(currentPage, pageSize), escola.getQuantidadeAlunos());
    }

    @Override
    public Page<Aluno> buscarAlunosAusentesNoRefeitorio(Escola escola, String nomeAluno, long etapaDeEnsinoId, LocalDate dia, Pageable pageable) {
        LocalDateTime diaInicio = dia.atStartOfDay();
        LocalDateTime diaFim = dia.plusDays(1).atStartOfDay();
        List<String> refeitorios = escola.getRefeitorios().stream().map(Refeitorio::getNumeroDispositivo).toList();
        List<String> locaisDeEntrada = escola.getLocaisDeEntrada().stream().map(LocalDeEntrada::getNumeroDispositivo).toList();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber() - 1;

        var queryAlunos = entityManager.createQuery(
                        "SELECT a FROM Aluno a " +
                                "WHERE a.personId IN " +
                                "(SELECT r.personId FROM Reconhecimento r " +
                                "WHERE r.dataDeCriacao BETWEEN :diaInicio " +
                                "AND :diaFim " +
                                (StringUtils.isNotBlank(nomeAluno) ? "AND a.nome LIKE :nome " : "") +
                                (etapaDeEnsinoId > 0 ? "AND a.etapaDeEnsino.id = :etapaDeEnsinoId " : "") +
                                "AND r.deviceKey IN (:locaisDeEntrada) " +
                                "AND a.personId NOT IN " +
                                "(SELECT r2.personId FROM Reconhecimento r2 " +
                                "WHERE r2.dataDeCriacao BETWEEN :diaInicio " +
                                "AND :diaFim " +
                                "AND r2.deviceKey IN (:refeitorios))) " +
                                "ORDER BY a.nome ASC")
                .setParameter("diaInicio", diaInicio)
                .setParameter("diaFim", diaFim)
                .setParameter("refeitorios", refeitorios)
                .setParameter("locaisDeEntrada", locaisDeEntrada)

                .setMaxResults(pageSize)
                .setFirstResult(currentPage * pageSize);

        if (StringUtils.isNotBlank(nomeAluno)) {
            queryAlunos.setParameter("nome", "%" + nomeAluno + "%");
        }

        if (etapaDeEnsinoId > 0) {
            queryAlunos.setParameter("etapaDeEnsinoId", etapaDeEnsinoId);
        }

        List<Aluno> alunos = queryAlunos.getResultList();
        return new PageImpl<>(alunos, PageRequest.of(currentPage, pageSize), escola.getQuantidadeAlunos());
    }


}
