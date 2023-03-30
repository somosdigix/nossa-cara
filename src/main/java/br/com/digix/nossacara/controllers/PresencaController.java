package br.com.digix.nossacara.controllers;

import java.time.LocalDate;

import br.com.digix.nossacara.dtos.RefeitorioResponseDTO;
import br.com.digix.nossacara.models.Refeitorio;
import br.com.digix.nossacara.repository.RefeitorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.nossacara.dtos.EntradaResponseDTO;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.repository.EscolaRepository;
import br.com.digix.nossacara.repository.LocalDeEntradaRepository;
import br.com.digix.nossacara.services.PresencaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = { "/api/v1/presencas" }, produces = { "application/json" })
public class PresencaController {

    @Autowired
    private PresencaService presencaService;

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private LocalDeEntradaRepository localDeEntradaRepository;

    @Autowired
    private RefeitorioRepository refeitorioRepository;

    @Operation(summary = "Buscar as presencas na entrada em uma escola em um dia")
    @ApiResponse(responseCode = "200")
    @GetMapping("/entradas")
    public ResponseEntity<EntradaResponseDTO> buscarPresencasNaEntrada(@RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
        LocalDeEntrada localDeEntrada = localDeEntradaRepository.findAll().get(0);
        Escola escola = escolaRepository.findAll().get(0);
        return ResponseEntity.ok().body(presencaService.buscarComparecimento(dia, localDeEntrada, escola));
    }

    @Operation(summary = "Buscar as presencas no refeitorio em uma escola em um dia")
    @ApiResponse(responseCode = "200")
    @GetMapping("/refeitorio")
    public ResponseEntity<RefeitorioResponseDTO> buscarPresencasNoRefeitorio(@RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
        Refeitorio refeitorio = refeitorioRepository.findAll().get(0);
        LocalDeEntrada localDeEntrada = localDeEntradaRepository.findAll().get(0);
        return ResponseEntity.ok().body(presencaService.buscarComparecimento(dia, refeitorio, localDeEntrada));
    }
}
