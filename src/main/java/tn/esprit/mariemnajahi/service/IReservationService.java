package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Reservation;

import java.util.List;

public interface IReservationService {
    default Reservation create(Reservation reservation) { return save(reservation); }

    Reservation save(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> findAll();

    Reservation update(Reservation reservation);
    default Reservation update(Long id, Reservation reservation) {
        reservation.setIdReservation(id);
        return update(reservation);
    }

    void deleteById(Long id);

    default Reservation addReservation(Reservation reservation) {
        return save(reservation);
    }

    default Reservation retrieveReservation(Long id) {
        return findById(id);
    }

    default List<Reservation> retrieveAllReservations() {
        return findAll();
    }

    default void deleteReservation(Long id) {
        deleteById(id);
    }
}
