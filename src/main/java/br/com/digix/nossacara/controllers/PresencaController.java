package br.com.digix.nossacara.controllers;

import br.com.digix.nossacara.dtos.EntradaResponseDTO;
import br.com.digix.nossacara.dtos.PresencaResponseDTO;
import br.com.digix.nossacara.dtos.RefeitorioResponseDTO;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.repository.EscolaRepository;
import br.com.digix.nossacara.services.PresencaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(path = {"/api/v1/presencas"}, produces = {"application/json"})
public class PresencaController {
    private final PresencaService presencaService;
    private final EscolaRepository escolaRepository;

    public PresencaController(PresencaService presencaService, EscolaRepository escolaRepository) {
        this.presencaService = presencaService;
        this.escolaRepository = escolaRepository;
    }

    @Operation(summary = "Buscar as presencas na entrada em uma escola em um dia")
    @ApiResponse(responseCode = "200")
    @GetMapping("/entradas")
    public ResponseEntity<EntradaResponseDTO> buscarPresencasNaEntrada(@RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
        Escola escola = escolaRepository.findAll().get(0);
        return ResponseEntity.ok().body(presencaService.buscarComparecimentoEntrada(dia, escola));
    }

    @Operation(summary = "Buscar as presencas no refeitorio em uma escola em um dia")
    @ApiResponse(responseCode = "200")
    @GetMapping("/refeitorio")
    public ResponseEntity<RefeitorioResponseDTO> buscarPresencasNoRefeitorio(@RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
        Escola escola = escolaRepository.findAll().get(0);
        return ResponseEntity.ok().body(presencaService.buscarComparecimentoRefeitorio(dia, escola));
    }

    @Operation(summary = "Buscar todas as presencas de entrada na escola e no refeitorio em um dia")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public ResponseEntity<PresencaResponseDTO> buscarTodas(@RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
        Escola escola = escolaRepository.findAll().get(0);
        return ResponseEntity.ok().body(presencaService.buscarComparecimento(dia, escola));
    }
}