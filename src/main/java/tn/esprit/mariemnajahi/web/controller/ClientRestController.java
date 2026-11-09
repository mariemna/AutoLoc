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
import tn.esprit.mariemnajahi.domain.Client;
import tn.esprit.mariemnajahi.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientRestController {

    private final IClientService clientService;

    @GetMapping
    @Operation(summary = "Lister les clients")
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un client")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un client")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(client));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        client.setIdClient(id);
        return ResponseEntity.ok(clientService.updateClient(client));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
