package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Paiement;

import java.util.List;

public interface IPaiementService {

    Paiement findById(Long id);

    List<Paiement> findAll();

    default Paiement retrievePaiement(Long id) {
        return findById(id);
    }

    default List<Paiement> retrieveAllPaiements() {
        return findAll();
    }
}
