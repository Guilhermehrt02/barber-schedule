package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Client;
import com.unifei.barber_schedule.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indicates that the class provides some business services
public class ClientService {

    @Autowired // field-based dependency injection. Be able to use the methods from the repository
    private ClientRepository clientRepository;

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    // find all clients
    public List<Client> findAll() {
        return clientRepository.findAll(); // Call the method findAll from the repository
    }

    // find client by id
    public Optional<Client> findById(int id) {
        return clientRepository.findById(id);
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
