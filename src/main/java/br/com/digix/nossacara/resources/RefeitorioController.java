package br.com.digix.nossacara.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.nossacara.models.Refeitorio;
import br.com.digix.nossacara.repository.RefeitorioRepository;

@RestController
@RequestMapping(path = { "/api/v1/refeitorios" }, produces = { "application/json" })
public class RefeitorioController {

    @Autowired
    private RefeitorioRepository refeitorioRepository;

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
