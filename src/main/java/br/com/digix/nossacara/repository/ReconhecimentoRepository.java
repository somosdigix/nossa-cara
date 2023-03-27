package br.com.digix.nossacara.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.models.Reconhecimento;

public interface ReconhecimentoRepository extends CrudRepository<Reconhecimento, Long> {

    public List<Reconhecimento> findAll();

    @Query("select count(DISTINCT r.personId) from Reconhecimento r where dataDeCriacao <= :dia and :localDeEntrada.numeroDispositivo")
    public int quantidadeDeReconhecimentosDistintos(LocalDate dia, LocalDeEntrada localDeEntrada);
    
}

// select count(DISTINCT person_id) from reconhecimento r
// where CONVERT(Date, data_de_criacao)<= CAST(N'2022-04-28' AS Date) and device_key = 1;