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
import tn.esprit.mariemnajahi.domain.Agence;
import tn.esprit.mariemnajahi.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@Tag(name = "Agences", description = "Gestion des agences")
public class AgenceRestController {

    private final IAgenceService agenceService;

    @GetMapping
    @Operation(summary = "Lister les agences")
    public ResponseEntity<List<Agence>> findAll() {
        return ResponseEntity.ok(agenceService.getAllAgences());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une agence")
    public ResponseEntity<Agence> findById(@PathVariable Long id) {
        return ResponseEntity.ok(agenceService.getAgenceById(id));
    }

    @PostMapping
    @Operation(summary = "Créer une agence")
    public ResponseEntity<Agence> create(@RequestBody Agence agence) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceService.createAgence(agence));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une agence")
    public ResponseEntity<Agence> update(@PathVariable Long id, @RequestBody Agence agence) {
        agence.setIdAgence(id);
        return ResponseEntity.ok(agenceService.updateAgence(agence));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une agence")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        agenceService.deleteAgence(id);
        return ResponseEntity.noContent().build();
    }
}
