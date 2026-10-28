package tn.esprit.mariemnajahi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mariemnajahi.domain.Client;
import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;
import tn.esprit.mariemnajahi.repository.IClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        ServiceValidation.requireEntity(client, "Client");
        ServiceValidation.requireNewId(client.getIdClient());
        validate(client);
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {
        ServiceValidation.requireId(id, "Client");
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found: " + id));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client update(Client client) {
        ServiceValidation.requireEntity(client, "Client");
        if (client.getIdClient() == null) {
            throw new IllegalArgumentException("An existing Client must have an id");
        }
        validate(client);
        Client existing = findById(client.getIdClient());
        existing.setNom(client.getNom());
        existing.setPrenom(client.getPrenom());
        existing.setEmail(client.getEmail());
        existing.setTelephone(client.getTelephone());
        existing.setNumPermis(client.getNumPermis());
        existing.setDateInscription(client.getDateInscription());
        return clientRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        ServiceValidation.requireId(id, "Client");
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found: " + id);
        }
        clientRepository.deleteById(id);
    }

    private void validate(Client client) {
        ServiceValidation.requireNonBlank(client.getNom(), "Client nom");
        ServiceValidation.requireNonBlank(client.getPrenom(), "Client prenom");
        ServiceValidation.requireNonBlank(client.getEmail(), "Client email");
        ServiceValidation.requireNonBlank(client.getNumPermis(), "Client numPermis");
        if (client.getDateInscription() == null) {
            throw new IllegalArgumentException("Client dateInscription must not be null");
        }
    }
}
