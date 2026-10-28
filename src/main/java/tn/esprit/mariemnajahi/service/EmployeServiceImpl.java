package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Employe;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe save(Employe employe) {
        ServiceValidation.requireEntity(employe, "Employe");
        ServiceValidation.requireNewId(employe.getIdEmploye());
        validate(employe);
        return employeRepository.save(employe);
    }

    @Override
    public Employe findById(Long id) {
        ServiceValidation.requireId(id, "Employe");
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe not found: " + id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe update(Employe employe) {
        ServiceValidation.requireEntity(employe, "Employe");
        if (employe.getIdEmploye() == null) {
            throw new IllegalArgumentException("An existing Employe must have an id");
        }
        validate(employe);
        Employe existing = findById(employe.getIdEmploye());
        existing.setNom(employe.getNom());
        existing.setPrenom(employe.getPrenom());
        existing.setRole(employe.getRole());
        return employeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        ServiceValidation.requireId(id, "Employe");
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe not found: " + id);
        }
        employeRepository.deleteById(id);
    }

    private void validate(Employe employe) {
        ServiceValidation.requireNonBlank(employe.getNom(), "Employe nom");
        ServiceValidation.requireNonBlank(employe.getPrenom(), "Employe prenom");
        if (employe.getRole() == null) {
            throw new IllegalArgumentException("Employe role must not be null");
        }
    }
}
