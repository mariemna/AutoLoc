package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Agence;

import java.util.List;

public interface IAgenceService {

    Agence save(Agence agence);

    Agence findById(Long id);

    List<Agence> findAll();

    Agence update(Agence agence);

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

    default void deleteAgence(Long id) {
        deleteById(id);
    }
}
