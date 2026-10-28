package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Contrat;

public interface IContratRepository extends JpaRepository<Contrat, Long> {
}
