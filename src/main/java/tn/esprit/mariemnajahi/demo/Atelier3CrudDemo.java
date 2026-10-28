package tn.esprit.mariemnajahi.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tn.esprit.mariemnajahi.domain.Client;
import tn.esprit.mariemnajahi.repository.IClientRepository;

@Component
@Profile("atelier3")
@RequiredArgsConstructor
public class Atelier3CrudDemo implements CommandLineRunner {

    private final IClientRepository clientRepository;

    @Override
    public void run(String... args) {
        Client client = new Client(
                null,
                "Demo",
                "Atelier3",
                "atelier3-" + System.currentTimeMillis() + "@example.com",
                null,
                "DEMO-" + System.currentTimeMillis(),
                LocalDate.now(),
                null
        );

        Client savedClient = clientRepository.save(client);
        System.out.println("[CRUD] save -> id=" + savedClient.getIdClient());

        clientRepository.findById(savedClient.getIdClient())
                .ifPresent(found -> System.out.println("[CRUD] findById -> " + found.getEmail()));

        clientRepository.deleteById(savedClient.getIdClient());
        System.out.println("[CRUD] deleteById -> exists=" +
                clientRepository.existsById(savedClient.getIdClient()));
    }
}
