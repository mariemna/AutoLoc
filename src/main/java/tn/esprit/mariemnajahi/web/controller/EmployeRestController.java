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
import tn.esprit.mariemnajahi.domain.Employe;
import tn.esprit.mariemnajahi.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
@Tag(name = "Employés", description = "Gestion des employés")
public class EmployeRestController {

    private final IEmployeService employeService;

    @GetMapping
    @Operation(summary = "Lister les employés")
    public ResponseEntity<List<Employe>> findAll() {
        return ResponseEntity.ok(employeService.getAllEmployes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un employé")
    public ResponseEntity<Employe> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.getEmployeById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un employé")
    public ResponseEntity<Employe> create(@RequestBody Employe employe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeService.createEmploye(employe));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un employé")
    public ResponseEntity<Employe> update(@PathVariable Long id, @RequestBody Employe employe) {
        employe.setIdEmploye(id);
        return ResponseEntity.ok(employeService.updateEmploye(employe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un employé")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeService.deleteEmploye(id);
        return ResponseEntity.noContent().build();
    }
}
