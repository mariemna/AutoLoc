package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Employe;

public interface IEmployeRepository extends JpaRepository<Employe, Long> {
}
