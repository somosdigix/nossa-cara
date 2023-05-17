package br.com.digix.nossacara.controllers;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.nossacara.dtos.EtapaDeEnsinoResponseDTO;
import br.com.digix.nossacara.models.EtapaDeEnsino;
import br.com.digix.nossacara.services.EtapaDeEnsinoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping(path = {"/api/v1/etapasDeEnsino"}, produces = {"application/json"})
@RestController
public class EtapaDeEnsinoController {
    private EtapaDeEnsinoService etapaDeEnsinoService;

    public EtapaDeEnsinoController(EtapaDeEnsinoService etapaDeEnsinoService) {
        this.etapaDeEnsinoService = etapaDeEnsinoService;
    }

    
    
    @ApiResponse(responseCode = "200")
    @GetMapping("/{id}")
    public ResponseEntity<EtapaDeEnsinoResponseDTO> buscarPorld(@PathVariable Long id)  {
        return ResponseEntity.ok(etapaDeEnsinoService.buscarPorId(id));
    }

}
