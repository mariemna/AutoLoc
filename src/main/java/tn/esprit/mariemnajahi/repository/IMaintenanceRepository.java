package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Maintenance;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
