package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Barber;
import com.unifei.barber_schedule.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberService {

    @Autowired // field-based dependency injection. Be able to use the methods from the repository
    private BarberRepository barberRepository;

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    // find all barbers
    public List<Barber> findAll() {
        return barberRepository.findAll();
    }

    // find barber by id
    public Optional<Barber> findById(int id){
        return barberRepository.findById(id);
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
