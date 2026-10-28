package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Paiement;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}
