package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Agence;

import java.util.List;

public interface IAgenceService {
    default Agence createAgence(Agence agence) { return save(agence); }
    default Agence getAgenceById(Long id) { return findById(id); }
    default Agence updateAgence(Agence agence) { return update(agence); }
    default void deleteAgence(Long id) { deleteById(id); }
    default List<Agence> getAllAgences() { return findAll(); }
    default Agence create(Agence agence) { return save(agence); }

    Agence save(Agence agence);

    Agence findById(Long id);

    List<Agence> findAll();

    Agence update(Agence agence);
    default Agence update(Long id, Agence agence) {
        agence.setIdAgence(id);
        return update(agence);
    }

    void deleteById(Long id);

    default Agence addAgence(Agence agence) {
        return save(agence);
    }

    default Agence retrieveAgence(Long id) {
        return findById(id);
    }

    default List<Agence> retrieveAllAgences() {
        return findAll();
    }

}
