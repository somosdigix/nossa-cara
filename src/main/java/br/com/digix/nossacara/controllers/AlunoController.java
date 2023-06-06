package br.com.digix.nossacara.controllers;

import java.time.LocalDate;

import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.repository.EscolaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.services.AlunoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final EscolaRepository escolaRepository;

    @GetMapping("/presenca")
    public ResponseEntity<ListagemAlunosResponseDTO> listarAlunosPresentes(
            @RequestParam(name = "dia") @DateTimeFormat(pattern = "dd-MM-yy") LocalDate dia,
            @RequestParam(name = "nome", required = false) String nomeAluno,
            @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(name = "pageSize", defaultValue = "15") int pageSize) {

        Escola escola = escolaRepository.findAll().get(0);
        ListagemAlunosResponseDTO listagem = alunoService.criarListaAlunosPresentes(dia, escola, nomeAluno, currentPage, pageSize);
        return ResponseEntity.ok(listagem);
    }
}