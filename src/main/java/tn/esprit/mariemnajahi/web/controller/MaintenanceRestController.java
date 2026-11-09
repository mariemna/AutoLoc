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
import tn.esprit.mariemnajahi.domain.Maintenance;
import tn.esprit.mariemnajahi.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances", description = "Gestion des maintenances")
public class MaintenanceRestController {

    private final IMaintenanceService maintenanceService;

    @GetMapping
    @Operation(summary = "Lister les maintenances")
    public ResponseEntity<List<Maintenance>> findAll() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenances());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une maintenance")
    public ResponseEntity<Maintenance> findById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }

    @PostMapping
    @Operation(summary = "Créer une maintenance")
    public ResponseEntity<Maintenance> create(@RequestBody Maintenance maintenance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.createMaintenance(maintenance));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une maintenance")
    public ResponseEntity<Maintenance> update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        maintenance.setIdMaintenance(id);
        return ResponseEntity.ok(maintenanceService.updateMaintenance(maintenance));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une maintenance")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
