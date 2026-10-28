package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Contrat;

import java.util.List;

public interface IContratService {

    Contrat save(Contrat contrat);

    Contrat findById(Long id);

    List<Contrat> findAll();

    Contrat update(Contrat contrat);

    void deleteById(Long id);

    default Contrat addContrat(Contrat contrat) {
        return save(contrat);
    }

    default Contrat retrieveContrat(Long id) {
        return findById(id);
    }

    default List<Contrat> retrieveAllContrats() {
        return findAll();
    }

    default void deleteContrat(Long id) {
        deleteById(id);
    }
}
