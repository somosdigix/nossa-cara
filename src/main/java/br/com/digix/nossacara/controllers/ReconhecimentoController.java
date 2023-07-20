package br.com.digix.nossacara.controllers;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoSucessResponseDTO;
import br.com.digix.nossacara.services.ReconhecimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/public/v1/reconhecimentos"}, produces = {"application/json"})
public class ReconhecimentoController {
    private final ReconhecimentoService reconhecimentoService;

    public ReconhecimentoController(ReconhecimentoService reconhecimentoService) {
        this.reconhecimentoService = reconhecimentoService;
    }

    @Operation(summary = "Cadastrar um novo reconhecimento facial")
    @ApiResponse(responseCode = "200")
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<ReconhecimentoSucessResponseDTO> cadastrar(@RequestBody ReconhecimentoRequestDTO novoReconhecimento) {
        return ResponseEntity.ok().body(reconhecimentoService.cadastrarRespostaOK(novoReconhecimento));
    }

    @Operation(summary = "Buscar todos os reconhecimentos faciais salvos")
    @ApiResponse(responseCode = "200")
    @GetMapping()
    public ResponseEntity<List<ReconhecimentoResponseDTO>> buscarTodos() {
        return ResponseEntity.ok().body(reconhecimentoService.buscarTodos());
    }
}