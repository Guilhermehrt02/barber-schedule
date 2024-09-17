package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    //Register a new client
    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody Client client) {

        Client newClient = clientService.registerClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    //Get all clients

    //Get client by id
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {

        Client client = clientService.getClientById(id);

        return ResponseEntity.ok(client);
    }

    //Update client
    @PutMapping()
    public ResponseEntity<Client> updateClient(@RequestBody Client updatedClient) {

        Client client = clientService.updateClient(updatedClient.getId(), updatedClient);

        return ResponseEntity.ok(client);
    }

    //Delete client
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {

        clientService.deleteClient(id);

        return ResponseEntity.ok("Client has been successfully deleted.");
    }

}
