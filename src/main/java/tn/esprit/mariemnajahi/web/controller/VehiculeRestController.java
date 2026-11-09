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
import tn.esprit.mariemnajahi.domain.Vehicule;
import tn.esprit.mariemnajahi.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
@Tag(name = "Véhicules", description = "Gestion des véhicules")
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @GetMapping
    @Operation(summary = "Lister les véhicules")
    public ResponseEntity<List<Vehicule>> findAll() {
        return ResponseEntity.ok(vehiculeService.getAllVehicules());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un véhicule")
    public ResponseEntity<Vehicule> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.getVehiculeById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un véhicule")
    public ResponseEntity<Vehicule> create(@RequestBody Vehicule vehicule) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.createVehicule(vehicule));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un véhicule")
    public ResponseEntity<Vehicule> update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        vehicule.setIdVehicule(id);
        return ResponseEntity.ok(vehiculeService.updateVehicule(vehicule));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un véhicule")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
