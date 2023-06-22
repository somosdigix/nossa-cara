package br.com.digix.nossacara.controllers;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.services.EtapaDeEnsinoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = {"/api/v1/etapas-de-ensino"}, produces = {"application/json"})
@RestController
@RequiredArgsConstructor
public class EtapaDeEnsinoController {
    private final EtapaDeEnsinoService etapaDeEnsinoService;

    @ApiResponse(responseCode = "200")
    @GetMapping("/{id}")
    public ResponseEntity<EtapaDeEnsinoResponseDTO> buscarPorld(@PathVariable Long id)  {
        return ResponseEntity.ok(etapaDeEnsinoService.buscarPorId(id));
    }

    @ApiResponse(responseCode = "200")
    @GetMapping
    public ResponseEntity<List<EtapaDeEnsinoResponseDTO>> buscarTodos()  {
        return ResponseEntity.ok(etapaDeEnsinoService.buscarTodos());
    }

}
