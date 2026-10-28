package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Vehicule;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IVehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeService implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule save(Vehicule vehicule) {
        ServiceValidation.requireEntity(vehicule, "Vehicule");
        ServiceValidation.requireNewId(vehicule.getIdVehicule());
        validate(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        ServiceValidation.requireId(id, "Vehicule");
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule not found: " + id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Vehicule vehicule) {
        ServiceValidation.requireEntity(vehicule, "Vehicule");
        if (vehicule.getIdVehicule() == null) {
            throw new IllegalArgumentException("An existing Vehicule must have an id");
        }
        validate(vehicule);
        Vehicule existing = findById(vehicule.getIdVehicule());
        existing.setImmatriculation(vehicule.getImmatriculation());
        existing.setMarque(vehicule.getMarque());
        existing.setModele(vehicule.getModele());
        existing.setCategorie(vehicule.getCategorie());
        existing.setTarifJournalier(vehicule.getTarifJournalier());
        existing.setStatut(vehicule.getStatut());
        return vehiculeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        ServiceValidation.requireId(id, "Vehicule");
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule not found: " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    private void validate(Vehicule vehicule) {
        ServiceValidation.requireNonBlank(vehicule.getImmatriculation(), "Vehicule immatriculation");
        ServiceValidation.requireNonBlank(vehicule.getMarque(), "Vehicule marque");
        ServiceValidation.requireNonBlank(vehicule.getModele(), "Vehicule modele");
        if (vehicule.getCategorie() == null) {
            throw new IllegalArgumentException("Vehicule categorie must not be null");
        }
        if (vehicule.getTarifJournalier() == null) {
            throw new IllegalArgumentException("Vehicule tarifJournalier must not be null");
        }
        if (vehicule.getTarifJournalier().signum() < 0) {
            throw new IllegalArgumentException("Vehicule tarifJournalier must not be negative");
        }
        if (vehicule.getStatut() == null) {
            throw new IllegalArgumentException("Vehicule statut must not be null");
        }
    }
}
