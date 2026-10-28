package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Paiement;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IPaiementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement createPaiement(Paiement paiement) {
        ServiceValidation.requireEntity(paiement, "Paiement");
        ServiceValidation.requireNewId(paiement.getIdPaiement());
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement findById(Long id) {
        ServiceValidation.requireId(id, "Paiement");
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement not found: " + id));
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement updatePaiement(Paiement paiement) {
        ServiceValidation.requireEntity(paiement, "Paiement");
        if (paiement.getIdPaiement() == null) {
            throw new IllegalArgumentException("An existing Paiement must have an id");
        }
        Paiement existing = findById(paiement.getIdPaiement());
        existing.setMontant(paiement.getMontant());
        existing.setDatePaiement(paiement.getDatePaiement());
        existing.setModePaiement(paiement.getModePaiement());
        return paiementRepository.save(existing);
    }

    @Override
    public void deletePaiement(Long id) {
        ServiceValidation.requireId(id, "Paiement");
        if (!paiementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paiement not found: " + id);
        }
        paiementRepository.deleteById(id);
    }
}
