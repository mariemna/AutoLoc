package tn.esprit.mariemnajahi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.mariemnajahi.domain.Contrat;
import tn.esprit.mariemnajahi.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats")
public class ContratRestController {

    private final IContratService contratService;

    @GetMapping
    @Operation(summary = "Lister les contrats")
    public ResponseEntity<List<Contrat>> findAll() {
        return ResponseEntity.ok(contratService.getAllContrats());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un contrat")
    public ResponseEntity<Contrat> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.getContratById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un contrat")
    public ResponseEntity<Contrat> create(@RequestBody Contrat contrat) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.createContrat(contrat));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un contrat")
    public ResponseEntity<Contrat> update(@PathVariable Long id, @RequestBody Contrat contrat) {
        contrat.setIdContrat(id);
        return ResponseEntity.ok(contratService.updateContrat(contrat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        contratService.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }
}
