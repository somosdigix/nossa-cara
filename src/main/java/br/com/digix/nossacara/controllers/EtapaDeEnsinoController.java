package br.com.digix.nossacara.controllers;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.services.EtapaDeEnsinoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = {"/api/v1/etapas-de-ensino"}, produces = {"application/json"})
@RestController
@CrossOrigin(value = "*")
@RequiredArgsConstructor
public class EtapaDeEnsinoController {
    private final EtapaDeEnsinoService etapaDeEnsinoService;

    @ApiResponse(responseCode = "200")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read:etapas-de-ensino')")
    public ResponseEntity<EtapaDeEnsinoResponseDTO> buscarPorld(@PathVariable Long id)  {
        return ResponseEntity.ok(etapaDeEnsinoService.buscarPorId(id));
    }


    @ApiResponse(responseCode = "200")
    @GetMapping
    @PreAuthorize("hasAuthority('read:etapas-de-ensino')")
    public ResponseEntity<List<EtapaDeEnsinoResponseDTO>> buscarTodos()  {
        return ResponseEntity.ok(etapaDeEnsinoService.buscarTodos());
    }
}
