package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Equipement;

public interface IEquipementRepository extends JpaRepository<Equipement, Long> {
}
