package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.entity.Client;
import com.unifei.barber_schedule.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AppointmentService appointmentService;

    @Autowired
    public ClientService(ClientRepository clientRepository, AppointmentService appointmentService) {
        this.clientRepository = clientRepository;
        this.appointmentService = appointmentService;
    }

    // Register a new client
    public Client registerClient(Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + client.getEmail());
        }
        client.setId(0); // Ensure the client is new
        return clientRepository.save(client);
    }

    // Get all clients
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // Get client by id
    public Client getClientById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id - " + id));
    }

    // Update client
    public Client updateClient(int id, Client updatedClient) {
        Optional<Client> result = clientRepository.findByEmail(updatedClient.getEmail());
        if (result.isPresent() && result.get().getId() != id) {
            throw new IllegalArgumentException("Email already registered - " + updatedClient.getEmail());
        }
        Client existingClient = getClientById(id);
        existingClient.setName(updatedClient.getName());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setPassword(updatedClient.getPassword());
        return clientRepository.save(existingClient);
    }

    // Delete client
    public void deleteClient(int clientId) {
        Client client = getClientById(clientId); // Will throw exception if not found
        appointmentService.deleteAppointmentsByClientId(clientId); // Delete related appointments
        clientRepository.delete(client);
    }

    // Get all appointments for a client
    public List<Appointment> getAppointmentsForClientId(int clientId) {
        getClientById(clientId); // Ensure client exists
        return appointmentService.getAppointmentsByClientId(clientId);
    }

    // Create a new appointment for a client
    public Appointment createAppointmentForClient(int clientId, Appointment appointment) {
        Client client = getClientById(clientId); // Ensure client exists
        appointment.setClient(client); // Associate the client with the appointment
        return appointmentService.createAppointment(appointment);
    }

    // Update an appointment for a client
    public Appointment updateAppointmentForClient(int clientId, int appointmentId, Appointment updatedAppointment) {
        Client client = getClientById(clientId); // Ensure client exists
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        if (!appointment.getClient().equals(client)) {
            throw new IllegalArgumentException("The appointment does not belong to the specified customer.");
        }
        appointment.setDate(updatedAppointment.getDate());
        appointment.setTime(updatedAppointment.getTime());
        appointment.setService(updatedAppointment.getService());
        appointment.setBarber(updatedAppointment.getBarber());
        return appointmentService.updateAppointment(appointment);
    }

    // Delete an appointment for a client
    public void deleteAppointmentForClient(int clientId, int appointmentId) {
        Client client = getClientById(clientId); // Ensure client exists
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        if (!appointment.getClient().equals(client)) {
            throw new IllegalArgumentException("The appointment does not belong to the specified customer.");
        }
        appointmentService.deleteAppointment(appointmentId);
    }
}
