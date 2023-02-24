package br.com.digix.nossacara.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.nossacara.dtos.ReconhecimentoRequestDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.services.ReconhecimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = { "/api/v1/reconhecimentos" }, produces = { "application/json" })
public class ReconhecimentoController {
  private final ReconhecimentoService reconhecimentoService;

  public ReconhecimentoController(ReconhecimentoService reconhecimentoService) {
    this.reconhecimentoService = reconhecimentoService;
  }

  @Operation(summary = "Cadastrar um novo reconhecimento facial")
  @ApiResponse(responseCode = "201")
  @PostMapping(consumes = { "application/json" })
  public ResponseEntity<ReconhecimentoResponseDTO> cadastrdarReconhecimento(@RequestBody ReconhecimentoRequestDTO novoReconhecimento) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(reconhecimentoService.cadastrar(novoReconhecimento));
  }
}
