package br.com.digix.nossacara.resources;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.nossacara.dtos.EntradaResponseDTO;
import br.com.digix.nossacara.dtos.ReconhecimentoResponseDTO;
import br.com.digix.nossacara.models.Escola;
import br.com.digix.nossacara.models.LocalDeEntrada;
import br.com.digix.nossacara.services.PresencaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = { "/api/v1/presencas" }, produces = { "application/json" })
public class PresencaController {

    @Autowired
    private PresencaService presencaService;

    @Operation(summary = "Buscar as presencas na entrada em uma escola em um dia")
    @ApiResponse(responseCode = "200")
    @GetMapping("/entradas")
    public ResponseEntity<EntradaResponseDTO> buscarPresencasNaEntrada(LocalDate dia, LocalDeEntrada localDeEntrada,
            Escola escola) {
        return ResponseEntity.ok().body(presencaService.buscarComparecimento(dia, localDeEntrada, escola));
    }
}
