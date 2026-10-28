package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Client;

import java.util.List;

public interface IClientService {
    default Client createClient(Client client) { return save(client); }
    default Client getClientById(Long id) { return findById(id); }
    default Client updateClient(Client client) { return update(client); }
    default void deleteClient(Long id) { deleteById(id); }
    default List<Client> getAllClients() { return findAll(); }
    default Client create(Client client) { return save(client); }

    Client save(Client client);

    Client findById(Long id);

    List<Client> findAll();

    Client update(Client client);
    default Client update(Long id, Client client) {
        client.setIdClient(id);
        return update(client);
    }

    void deleteById(Long id);

    default Client addClient(Client client) {
        return save(client);
    }

    default Client retrieveClient(Long id) {
        return findById(id);
    }

    default List<Client> retrieveAllClients() {
        return findAll();
    }

}
