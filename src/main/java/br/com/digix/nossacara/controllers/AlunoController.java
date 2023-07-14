package br.com.digix.nossacara.controllers;

import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.repository.EscolaRepository;
import br.com.digix.nossacara.repository.EtapaDeEnsinoRepository;
import br.com.digix.nossacara.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final EscolaRepository escolaRepository;
    private final EtapaDeEnsinoRepository etapaDeEnsinoRepository;

    @GetMapping("/presenca")
    public ResponseEntity<ListagemAlunosResponseDTO> listarAlunosPresentes(
            @RequestParam(name = "dia") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dia,
            @RequestParam(name = "nome", required = false) String nomeAluno,
            @RequestParam(name = "etapaDeEnsinoId", defaultValue = "0") long etapaDeEnsinoId,
            @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        var escola = escolaRepository.findAll().get(0);
        var listagem = alunoService.criarListaAlunosPresentes(dia, escola, nomeAluno, etapaDeEnsinoId, currentPage, pageSize);
        return ResponseEntity.ok(listagem);
    }
    @GetMapping("/presenca/entrada")
    public ResponseEntity<ListagemAlunosResponseDTO> listarAlunosPresentesNaEntrada(
            @RequestParam(name = "dia") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dia,
            @RequestParam(name = "nome", required = false) String nomeAluno,
            @RequestParam(name = "etapaDeEnsinoId", defaultValue = "0") long etapaDeEnsinoId,
            @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        var escola = escolaRepository.findAll().get(0);
        var listagem = alunoService.criarListaAlunosPresentesNaEntrada(dia, escola, nomeAluno, etapaDeEnsinoId, currentPage, pageSize);
        return ResponseEntity.ok(listagem);
    }
    @GetMapping("/presenca/refeitorio")
    public ResponseEntity<ListagemAlunosResponseDTO> listarAlunosPresentesNoRefeitorio(
            @RequestParam(name = "dia") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dia,
            @RequestParam(name = "nome", required = false) String nomeAluno,
            @RequestParam(name = "etapaDeEnsinoId", defaultValue = "0") long etapaDeEnsinoId,
            @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        var escola = escolaRepository.findAll().get(0);
        var listagem = alunoService.criarListaAlunosPresentesNoRefeitorio(dia, escola, nomeAluno, etapaDeEnsinoId, currentPage, pageSize);
        return ResponseEntity.ok(listagem);
    }
}