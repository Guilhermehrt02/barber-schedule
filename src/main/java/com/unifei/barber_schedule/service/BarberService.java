package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Barber;
import com.unifei.barber_schedule.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberService {

    private BarberRepository barberRepository;

    @Autowired // Constructor-based dependency injection. Be able to use the methods from the repository without creating a new instance of it.
    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    // find all barbers
    public List<Barber> findAll() {
        return barberRepository.findAll();
    }

    // find barber by id
    public Barber findById(int id){
        Optional<Barber> result = barberRepository.findById(id);

        Barber barber = null;

        if (result.isPresent()) {
            barber = result.get();
        } else {
            throw new RuntimeException("Barber id not found - " + id);
        }

        return barber;
    }

    // save barber
    public Barber save(Barber barber){
        return barberRepository.save(barber);
    }

    // delete barber
    public void deleteById(int id){
        barberRepository.deleteById(id);
    }
}
