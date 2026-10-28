package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Paiement;

import java.util.List;

public interface IPaiementService {

    Paiement createPaiement(Paiement paiement);
    Paiement findById(Long id);
    default Paiement getPaiementById(Long id) { return findById(id); }

    List<Paiement> findAll();
    default List<Paiement> getAllPaiements() { return findAll(); }

    Paiement updatePaiement(Paiement paiement);
    void deletePaiement(Long id);

    default Paiement retrievePaiement(Long id) {
        return findById(id);
    }

    default List<Paiement> retrieveAllPaiements() {
        return findAll();
    }
}
