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
import tn.esprit.mariemnajahi.domain.Equipement;
import tn.esprit.mariemnajahi.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
@Tag(name = "Équipements", description = "Gestion des équipements")
public class EquipementRestController {

    private final IEquipementService equipementService;

    @GetMapping
    @Operation(summary = "Lister les équipements")
    public ResponseEntity<List<Equipement>> findAll() {
        return ResponseEntity.ok(equipementService.getAllEquipements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un équipement")
    public ResponseEntity<Equipement> findById(@PathVariable Long id) {
        return ResponseEntity.ok(equipementService.getEquipementById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un équipement")
    public ResponseEntity<Equipement> create(@RequestBody Equipement equipement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementService.createEquipement(equipement));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un équipement")
    public ResponseEntity<Equipement> update(@PathVariable Long id, @RequestBody Equipement equipement) {
        equipement.setIdEquipement(id);
        return ResponseEntity.ok(equipementService.updateEquipement(equipement));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un équipement")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
