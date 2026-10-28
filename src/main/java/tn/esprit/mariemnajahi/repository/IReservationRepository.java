package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
