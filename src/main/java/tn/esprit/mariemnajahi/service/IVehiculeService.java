package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {
    default Vehicule create(Vehicule vehicule) { return save(vehicule); }

    Vehicule save(Vehicule vehicule);

    Vehicule findById(Long id);

    List<Vehicule> findAll();

    Vehicule update(Vehicule vehicule);
    default Vehicule update(Long id, Vehicule vehicule) {
        vehicule.setIdVehicule(id);
        return update(vehicule);
    }

    void deleteById(Long id);

    default Vehicule addVehicule(Vehicule vehicule) {
        return save(vehicule);
    }

    default Vehicule retrieveVehicule(Long id) {
        return findById(id);
    }

    default List<Vehicule> retrieveAllVehicules() {
        return findAll();
    }

    default void deleteVehicule(Long id) {
        deleteById(id);
    }
}
