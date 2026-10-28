package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.domain.Client;

import java.util.List;

public interface IClientService {

    Client save(Client client);

    Client findById(Long id);

    List<Client> findAll();

    Client update(Client client);

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

    default void deleteClient(Long id) {
        deleteById(id);
    }
}
