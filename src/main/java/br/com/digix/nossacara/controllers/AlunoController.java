package br.com.digix.nossacara.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.nossacara.dtos.ListagemAlunosResponseDTO;
import br.com.digix.nossacara.services.AlunoService;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/presenca")
    public ResponseEntity<ListagemAlunosResponseDTO> listarAlunosPresentes(
            @RequestParam(name = "pageSize", defaultValue = "15") int pageSize,
            @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(name = "dia") @DateTimeFormat(pattern = "dd-MM-yy") LocalDate dia) {

        ListagemAlunosResponseDTO listagem = alunoService.criarListaAlunosPresentes(dia, null, currentPage, pageSize);

        return ResponseEntity.ok(listagem);
    }
}