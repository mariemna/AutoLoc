package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Contrat;

import java.util.List;

public interface IContratService {
    default Contrat create(Contrat contrat) { return save(contrat); }

    Contrat save(Contrat contrat);

    Contrat findById(Long id);

    List<Contrat> findAll();

    Contrat update(Contrat contrat);
    default Contrat update(Long id, Contrat contrat) {
        contrat.setIdContrat(id);
        return update(contrat);
    }

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
