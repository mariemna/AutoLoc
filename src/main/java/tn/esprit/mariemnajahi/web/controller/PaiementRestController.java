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
import tn.esprit.mariemnajahi.domain.Paiement;
import tn.esprit.mariemnajahi.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Gestion des paiements")
public class PaiementRestController {

    private final IPaiementService paiementService;

    @GetMapping
    @Operation(summary = "Lister les paiements")
    public ResponseEntity<List<Paiement>> findAll() {
        return ResponseEntity.ok(paiementService.getAllPaiements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un paiement")
    public ResponseEntity<Paiement> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.getPaiementById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un paiement")
    public ResponseEntity<Paiement> create(@RequestBody Paiement paiement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.createPaiement(paiement));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    public ResponseEntity<Paiement> update(@PathVariable Long id, @RequestBody Paiement paiement) {
        paiement.setIdPaiement(id);
        return ResponseEntity.ok(paiementService.updatePaiement(paiement));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        paiementService.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
