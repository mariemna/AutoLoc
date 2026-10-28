package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Contrat;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IContratRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratService implements IContratService {

    private final IContratRepository contratRepository;

    @Override
    public Contrat save(Contrat contrat) {
        ServiceValidation.requireEntity(contrat, "Contrat");
        ServiceValidation.requireNewId(contrat.getIdContrat());
        validate(contrat, true);
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat findById(Long id) {
        ServiceValidation.requireId(id, "Contrat");
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat not found: " + id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat update(Contrat contrat) {
        ServiceValidation.requireEntity(contrat, "Contrat");
        if (contrat.getIdContrat() == null) {
            throw new IllegalArgumentException("An existing Contrat must have an id");
        }
        validate(contrat, false);
        Contrat existing = findById(contrat.getIdContrat());
        existing.setDateSignature(contrat.getDateSignature());
        existing.setMontantTotal(contrat.getMontantTotal());
        existing.setValide(contrat.isValide());
        return contratRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        ServiceValidation.requireId(id, "Contrat");
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat not found: " + id);
        }
        contratRepository.deleteById(id);
    }

    private void validate(Contrat contrat, boolean requireReservation) {
        if (contrat.getDateSignature() == null) {
            throw new IllegalArgumentException("Contrat dateSignature must not be null");
        }
        if (contrat.getMontantTotal() == null) {
            throw new IllegalArgumentException("Contrat montantTotal must not be null");
        }
        if (contrat.getMontantTotal().signum() < 0) {
            throw new IllegalArgumentException("Contrat montantTotal must not be negative");
        }
        if (requireReservation && contrat.getReservation() == null) {
            throw new IllegalArgumentException("Contrat reservation must not be null");
        }
    }
}
