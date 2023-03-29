package br.com.digix.nossacara.repository;

import br.com.digix.nossacara.models.Reconhecimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReconhecimentoRepository extends CrudRepository<Reconhecimento, Long> {

    List<Reconhecimento> findAll();

    @Query(value = "select count(DISTINCT r.personId) from Reconhecimento r where cast(r.dataDeCriacao as LocalDate) = :dia and r.deviceKey = :numeroDispositivo")
    int quantidadeDeReconhecimentosDistintos(@Param("dia") LocalDate dia, @Param("numeroDispositivo") String numeroDispositivo);
    
    Reconhecimento findFirstByPersonIdOrderByIdDesc(String personId);

}

// select count(DISTINCT person_id) from reconhecimento r
// where CONVERT(Date, data_de_criacao)<= CAST(N'2022-04-28' AS Date) and device_key = 1;