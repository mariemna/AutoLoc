package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {

    Maintenance save(Maintenance maintenance);

    Maintenance findById(Long id);

    List<Maintenance> findAll();

    Maintenance update(Maintenance maintenance);

    void deleteById(Long id);

    default Maintenance addMaintenance(Maintenance maintenance) {
        return save(maintenance);
    }

    default Maintenance retrieveMaintenance(Long id) {
        return findById(id);
    }

    default List<Maintenance> retrieveAllMaintenances() {
        return findAll();
    }

    default void deleteMaintenance(Long id) {
        deleteById(id);
    }
}
