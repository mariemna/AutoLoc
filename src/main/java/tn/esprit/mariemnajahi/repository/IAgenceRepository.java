package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Agence;

public interface IAgenceRepository extends JpaRepository<Agence, Long> {
}
