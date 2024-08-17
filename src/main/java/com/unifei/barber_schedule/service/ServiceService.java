package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    @Autowired // field-based dependency injection. Be able to use the methods from the repository
    private ServiceRepository serviceRepository;

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    // find all services
    public List<com.unifei.barber_schedule.entity.Service> findAll() {
        return serviceRepository.findAll();
    }

    // find service by id
    public Optional<com.unifei.barber_schedule.entity.Service> findById(int id){
        return serviceRepository.findById(id);
    }

    // save service
    public com.unifei.barber_schedule.entity.Service save(com.unifei.barber_schedule.entity.Service service){
        return serviceRepository.save(service);
    }

    // delete service
    public void deleteById(int id){
        serviceRepository.deleteById(id);
    }
}
