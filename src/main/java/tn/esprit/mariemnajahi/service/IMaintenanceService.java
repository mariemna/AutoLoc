package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    default Maintenance createMaintenance(Maintenance maintenance) { return save(maintenance); }
    default Maintenance getMaintenanceById(Long id) { return findById(id); }
    default Maintenance updateMaintenance(Maintenance maintenance) { return update(maintenance); }
    default void deleteMaintenance(Long id) { deleteById(id); }
    default List<Maintenance> getAllMaintenances() { return findAll(); }
    default Maintenance create(Maintenance maintenance) { return save(maintenance); }

    Maintenance save(Maintenance maintenance);

    Maintenance findById(Long id);

    List<Maintenance> findAll();

    Maintenance update(Maintenance maintenance);
    default Maintenance update(Long id, Maintenance maintenance) {
        maintenance.setIdMaintenance(id);
        return update(maintenance);
    }

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

}
