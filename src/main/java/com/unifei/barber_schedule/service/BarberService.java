package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.domain.user.UserRole;
import com.unifei.barber_schedule.domain.barber.Barber;
import com.unifei.barber_schedule.repository.BarberRepository;
import com.unifei.barber_schedule.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberService {

    private final BarberRepository barberRepository;
    private final ServiceRepository serviceRepository;

    @Autowired // Constructor-based dependency injection. Be able to use the methods from the repository without creating a new instance of it.
    public BarberService(BarberRepository barberRepository,
                         ServiceRepository serviceRepository) {
        this.barberRepository = barberRepository;
        this.serviceRepository = serviceRepository;
    }

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    //register a new barber
    public Barber registerBarber(Barber barber) {

        if (barberRepository.findByEmail(barber.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + barber.getEmail());
        }

        barber.setId(0); // Ensure the barber is new
        barber.setRole(UserRole.BARBER);

        return barberRepository.save(barber);
    }

    //get barber by id
    public Barber getBarberById(int id) {

        return barberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Barber not found with id - " + id));
    }

    //get all barbers
    public List<Barber> findAll() {
        return barberRepository.findAll();
    }

    //get barbers by service
    public List<Barber> getBarbersByService(int serviceId) {

        com.unifei.barber_schedule.entity.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        return barberRepository.findByServices_Id(serviceId);
    }

    //update barber
    public Barber updateBarber(Barber updatedBarber) {

        Optional<Barber> result = barberRepository.findByEmail(updatedBarber.getEmail());

        if (result.isPresent() && result.get().getId() != updatedBarber.getId()) {
            throw new IllegalArgumentException("Email already registered - " + updatedBarber.getEmail());
        }

        Barber existingBarber = getBarberById(updatedBarber.getId());
        existingBarber.setName(updatedBarber.getName());
        existingBarber.setEmail(updatedBarber.getEmail());
        existingBarber.setPhone(updatedBarber.getPhone());
        existingBarber.setPassword(updatedBarber.getPassword());

        return barberRepository.save(existingBarber);
    }

    //delete barber
    public void deleteBarber(int barberId) {

        Barber barber = getBarberById(barberId); // Will throw exception if not found
        barberRepository.delete(barber);
    }

    //vincular barbeiro a um serviço
    public void assignServiceToBarber(int barberId, int serviceId) {
        // Busca o barbeiro pelo ID
        Barber barber = barberRepository.findById(barberId)
                .orElseThrow(() -> new IllegalArgumentException("Barber not found"));

        // Busca o serviço pelo ID
        com.unifei.barber_schedule.entity.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        // Verifica se o barbeiro já está vinculado ao serviço
        if (barber.getServices().contains(service)) {
            throw new IllegalArgumentException("The barber is already assigned to this service");
        }

        // Adiciona o serviço à lista de serviços do barbeiro
        barber.getServices().add(service);

        // Salva o barbeiro atualizado
        barberRepository.save(barber);
    }

}