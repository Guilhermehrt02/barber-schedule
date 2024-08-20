package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.entity.Service;
import com.unifei.barber_schedule.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Here we can create the methods that will be called by the client side.

    //Get all services
    @GetMapping
    public List<Service> findAll() {
        return serviceService.findAll();
    }

    //Get service by id
    @GetMapping("/{serviceId}")
    public Service getService(@PathVariable int serviceId) {

        Service service = serviceService.findById(serviceId);

        if (service == null) {
            throw new RuntimeException("Service id not found - " + serviceId);
        }
        return service;
    }

    //Create service
    @PostMapping
    public Service addService(@RequestBody Service service) {

        service.setId(0); // This way we can save a new service instead of updating an existing one
        Service dbService = serviceService.save(service);

        return dbService;
    }

    //Update service
    @PutMapping
    public Service updateService(@RequestBody Service service) {

        Service dbService = serviceService.findById(service.getId());

        dbService.setName(service.getName());
        dbService.setPrice(service.getPrice());
        dbService.setDuration(service.getDuration());

        return serviceService.save(dbService);
    }

    //Delete service
    public String deleteService(@PathVariable int serviceId) {

        Service service = serviceService.findById(serviceId);

        if (service == null) {
            throw new RuntimeException("Service id not found - " + serviceId);
        }

        serviceService.deleteById(serviceId);

        return "Deleted service id - " + serviceId;
    }
}
