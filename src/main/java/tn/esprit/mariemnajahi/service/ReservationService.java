package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Reservation;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation save(Reservation reservation) {
        ServiceValidation.requireEntity(reservation, "Reservation");
        ServiceValidation.requireNewId(reservation.getIdReservation());
        validate(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        ServiceValidation.requireId(id, "Reservation");
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found: " + id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Reservation reservation) {
        ServiceValidation.requireEntity(reservation, "Reservation");
        if (reservation.getIdReservation() == null) {
            throw new IllegalArgumentException("An existing Reservation must have an id");
        }
        validate(reservation);
        Reservation existing = findById(reservation.getIdReservation());
        existing.setDateDebut(reservation.getDateDebut());
        existing.setDateFin(reservation.getDateFin());
        existing.setStatut(reservation.getStatut());
        return reservationRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        ServiceValidation.requireId(id, "Reservation");
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation not found: " + id);
        }
        reservationRepository.deleteById(id);
    }

    private void validate(Reservation reservation) {
        if (reservation.getDateDebut() == null) {
            throw new IllegalArgumentException("Reservation dateDebut must not be null");
        }
        if (reservation.getDateFin() == null) {
            throw new IllegalArgumentException("Reservation dateFin must not be null");
        }
        if (reservation.getStatut() == null) {
            throw new IllegalArgumentException("Reservation statut must not be null");
        }
        ServiceValidation.requireDateRange(
                reservation.getDateDebut(), reservation.getDateFin(), "Reservation");
    }
}
