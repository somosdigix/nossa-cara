package br.com.digix.nossacara.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.services.ReconhecimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = { "/api/v1/reconhecimentos" }, produces = { "application/json" })
public class ReconhecimentoController {
    @Autowired
  private ReconhecimentoService reconhecimentoService;

  @Operation(summary = "Cadastrar um novo reconhecimento facial")
  @ApiResponse(responseCode = "201")
  @PostMapping(consumes = { "application/json" })
  public ResponseEntity<ReconhecimentoResponseDTO> cadastrar(@RequestBody ReconhecimentoRequestDTO novoReconhecimento) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(reconhecimentoService.cadastrar(novoReconhecimento));
  }

  @Operation(summary = "Buscar todos os reconhecimentos faciais salvos")
  @ApiResponse(responseCode = "200")
  @GetMapping()
  public ResponseEntity<List<ReconhecimentoResponseDTO>> buscarTodos() {
    return ResponseEntity.ok().body(reconhecimentoService.buscarTodos());
  }
}
