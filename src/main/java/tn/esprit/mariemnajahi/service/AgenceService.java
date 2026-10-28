package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Agence;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceService implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence save(Agence agence) {
        ServiceValidation.requireEntity(agence, "Agence");
        ServiceValidation.requireNewId(agence.getIdAgence());
        validate(agence);
        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {
        ServiceValidation.requireId(id, "Agence");
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence not found: " + id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Agence agence) {
        ServiceValidation.requireEntity(agence, "Agence");
        if (agence.getIdAgence() == null) {
            throw new IllegalArgumentException("An existing Agence must have an id");
        }
        validate(agence);
        Agence existing = findById(agence.getIdAgence());
        existing.setNom(agence.getNom());
        existing.setVille(agence.getVille());
        existing.setAdresse(agence.getAdresse());
        existing.setTelephone(agence.getTelephone());
        return agenceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        ServiceValidation.requireId(id, "Agence");
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence not found: " + id);
        }
        agenceRepository.deleteById(id);
    }

    private void validate(Agence agence) {
        ServiceValidation.requireNonBlank(agence.getNom(), "Agence nom");
        ServiceValidation.requireNonBlank(agence.getVille(), "Agence ville");
        ServiceValidation.requireNonBlank(agence.getAdresse(), "Agence adresse");
    }
}
