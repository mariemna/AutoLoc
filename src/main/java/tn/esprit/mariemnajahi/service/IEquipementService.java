package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Equipement;

import java.util.List;

public interface IEquipementService {
    default Equipement create(Equipement equipement) { return save(equipement); }

    Equipement save(Equipement equipement);

    Equipement findById(Long id);

    List<Equipement> findAll();

    Equipement update(Equipement equipement);
    default Equipement update(Long id, Equipement equipement) {
        equipement.setIdEquipement(id);
        return update(equipement);
    }

    void deleteById(Long id);

    default Equipement addEquipement(Equipement equipement) {
        return save(equipement);
    }

    default Equipement retrieveEquipement(Long id) {
        return findById(id);
    }

    default List<Equipement> retrieveAllEquipements() {
        return findAll();
    }

    default void deleteEquipement(Long id) {
        deleteById(id);
    }
}
