package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Equipement;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IEquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementService implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement save(Equipement equipement) {
        ServiceValidation.requireEntity(equipement, "Equipement");
        ServiceValidation.requireNewId(equipement.getIdEquipement());
        validate(equipement);
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement findById(Long id) {
        ServiceValidation.requireId(id, "Equipement");
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement not found: " + id));
    }

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement update(Equipement equipement) {
        ServiceValidation.requireEntity(equipement, "Equipement");
        if (equipement.getIdEquipement() == null) {
            throw new IllegalArgumentException("An existing Equipement must have an id");
        }
        validate(equipement);
        Equipement existing = findById(equipement.getIdEquipement());
        existing.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        ServiceValidation.requireId(id, "Equipement");
        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipement not found: " + id);
        }
        equipementRepository.deleteById(id);
    }

    private void validate(Equipement equipement) {
        ServiceValidation.requireNonBlank(equipement.getLibelle(), "Equipement libelle");
    }
}
