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
import tn.esprit.mariemnajahi.domain.Reservation;
import tn.esprit.mariemnajahi.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Tag(name = "Réservations", description = "Gestion des réservations")
public class ReservationRestController {

    private final IReservationService reservationService;

    @GetMapping
    @Operation(summary = "Lister les réservations")
    public ResponseEntity<List<Reservation>> findAll() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une réservation")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping
    @Operation(summary = "Créer une réservation")
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservation));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une réservation")
    public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody Reservation reservation) {
        reservation.setIdReservation(id);
        return ResponseEntity.ok(reservationService.updateReservation(reservation));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une réservation")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
