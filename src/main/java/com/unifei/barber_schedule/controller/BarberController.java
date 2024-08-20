package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.entity.Barber;
import com.unifei.barber_schedule.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {

    private BarberService barberService;

    @Autowired // Dependency Injection. Be able to use the service methods without creating a new instance of it.
    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    // Here we can create the methods that will be called by the client side.

    //Get all barbers
    @GetMapping
    public List<Barber> findAll() {
        return barberService.findAll();
    }

    //Get barber by id
    @GetMapping("/{barberId}")
    public Barber getBarber(@PathVariable int barberId) {

        Barber barber = barberService.findById(barberId);

        if(barber==null){
            throw new RuntimeException("Barber id not found - " + barberId);
        }

        return barber;
    }

    //Create barber
    @PostMapping
    public Barber createBarber(@RequestBody Barber barber) {

        barber.setId(0); // This way we can save a new barber instead of updating an existing one
        Barber dbBarber = barberService.save(barber);

        return dbBarber;
    }

    //Update barber
    @PutMapping
    public Barber updateBarber(@RequestBody Barber barber) {

        Barber dbBarber = barberService.findById(barber.getId());

        dbBarber.setName(barber.getName());
        dbBarber.setEmail(barber.getEmail());

        return barberService.save(dbBarber);
    }

    //Delete barber
    @DeleteMapping("/{barberId}")
    public String deleteBarber(@PathVariable int barberId) {

        Barber barber = barberService.findById(barberId);

        if(barber==null){
            throw new RuntimeException("Barber id not found - " + barberId);
        }

        barberService.deleteById(barberId);

        return "Deleted barber id - " + barberId;
    }
}
