package br.com.digix.nossacara.controllers;

import br.com.digix.nossacara.models.Refeitorio;
import br.com.digix.nossacara.repository.RefeitorioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = {"/api/v1/refeitorios"}, produces = {"application/json"})
public class RefeitorioController {
    private final RefeitorioRepository refeitorioRepository;

    public RefeitorioController(RefeitorioRepository refeitorioRepository) {
        this.refeitorioRepository = refeitorioRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Refeitorio>> buscarTodos() {
        Iterable<Refeitorio> iterable = refeitorioRepository.findAll();
        List<Refeitorio> refeitorios = new ArrayList<>();
        iterable.forEach(refeitorios::add);
        return ResponseEntity.ok().body(refeitorios);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Refeitorio> criar(@RequestBody Refeitorio refeitorio) {
        Refeitorio novoRefeitorio = refeitorioRepository.save(refeitorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRefeitorio);
    }
}