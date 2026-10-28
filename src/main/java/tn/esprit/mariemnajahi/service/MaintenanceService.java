package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Maintenance;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IMaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance save(Maintenance maintenance) {
        ServiceValidation.requireEntity(maintenance, "Maintenance");
        ServiceValidation.requireNewId(maintenance.getIdMaintenance());
        validate(maintenance);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance findById(Long id) {
        ServiceValidation.requireId(id, "Maintenance");
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance not found: " + id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance update(Maintenance maintenance) {
        ServiceValidation.requireEntity(maintenance, "Maintenance");
        if (maintenance.getIdMaintenance() == null) {
            throw new IllegalArgumentException("An existing Maintenance must have an id");
        }
        validate(maintenance);
        Maintenance existing = findById(maintenance.getIdMaintenance());
        existing.setDateDebut(maintenance.getDateDebut());
        existing.setDateFin(maintenance.getDateFin());
        existing.setDescription(maintenance.getDescription());
        return maintenanceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        ServiceValidation.requireId(id, "Maintenance");
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance not found: " + id);
        }
        maintenanceRepository.deleteById(id);
    }

    private void validate(Maintenance maintenance) {
        if (maintenance.getDateDebut() == null) {
            throw new IllegalArgumentException("Maintenance dateDebut must not be null");
        }
        ServiceValidation.requireDateRange(
                maintenance.getDateDebut(), maintenance.getDateFin(), "Maintenance");
    }
}
