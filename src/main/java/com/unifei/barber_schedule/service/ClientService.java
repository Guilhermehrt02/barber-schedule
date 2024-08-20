package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Client;
import com.unifei.barber_schedule.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indicates that the class provides some business services
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired // Constructor-based dependency injection. Be able to use the methods from the repository without creating a new instance of it.
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    // find all clients
    public List<Client> findAll() {
        return clientRepository.findAll(); // Call the method findAll from the repository
    }

    // find client by id
    public Client findById(int id) {
        Optional<Client> result = clientRepository.findById(id);

        Client client = null;

        if (result.isPresent()) {
            client = result.get();
        } else {
            throw new RuntimeException("Client id not found - " + id);
        }

        return client;
    }

    // save client
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    // delete client
    public void deleteById(int id){
        clientRepository.deleteById(id);
    }
}
