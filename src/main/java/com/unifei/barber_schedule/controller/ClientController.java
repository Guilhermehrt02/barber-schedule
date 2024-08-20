package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.unifei.barber_schedule.entity.Client;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired // Dependency Injection. Be able to use the service methods without creating a new instance of it.
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Here we can create the methods that will be called by the client side.

    //Get all clients
    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    //Get client by id
    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable int clientId) {

        Client client = clientService.findById(clientId);

        if (client == null) {
            throw new RuntimeException("Client id not found - " + clientId);
        }
        return client;
    }

    //Create client
    @PostMapping
    public Client addClient(@RequestBody Client client) {

        client.setId(0); // This way we can save a new client instead of updating an existing one
        Client dbClient = clientService.save(client);

        return dbClient;
    }

    //Update client
    @PutMapping
    public Client updateClient(@RequestBody Client client) {

//        Client dbClient = clientService.save(client);
//
//        return dbClient;

        Client existingClient = clientService.findById(client.getId());

        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        existingClient.setPhone(client.getPhone());
        existingClient.setPassword(client.getPassword());

        return clientService.save(existingClient);
    }

    //Delete client
    @DeleteMapping("/{clientId}")
    public String deleteClient(@PathVariable int clientId) {

        Client client = clientService.findById(clientId);

        if (client == null) {
            throw new RuntimeException("Client id not found - " + clientId);
        }

        clientService.deleteById(clientId);

        return "Deleted client id - " + clientId;
    }

}
