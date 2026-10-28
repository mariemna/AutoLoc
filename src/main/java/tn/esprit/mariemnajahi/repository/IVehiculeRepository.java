package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Vehicule;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
}
