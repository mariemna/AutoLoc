package tn.esprit.mariemnajahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.mariemnajahi.domain.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
}
