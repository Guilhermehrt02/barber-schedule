package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.domain.barber.Barber;
import com.unifei.barber_schedule.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {

    private final BarberService barberService;

    @Autowired // Dependency Injection. Be able to use the service methods without creating a new instance of it.
    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    // Here we can create the methods that will be called by the client side.

    //register a new barber
    @PostMapping("/register")
    public ResponseEntity<?> registerBarber(@RequestBody Barber barber) {

        Barber newBarber = barberService.registerBarber(barber);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBarber);
    }

    //Get all barbers
    @GetMapping
    public ResponseEntity<List<Barber>> findAll() {

        List<Barber> barbers = barberService.findAll();
        return ResponseEntity.ok(barbers);
    }

    //Get barber by id
    @GetMapping("/{barberId}")
    public ResponseEntity<Barber> getBarber(@PathVariable int barberId) {

        Barber barber = barberService.getBarberById(barberId);

        return ResponseEntity.ok(barber);
    }

    //Get barbers by service id
    @GetMapping("/services/{serviceId}")
    public ResponseEntity<List<Barber>> getBarbersByServiceId(@PathVariable int serviceId) {

        List<Barber> barbers = barberService.getBarbersByService(serviceId);

        return ResponseEntity.ok(barbers);
    }

    //Update barber
    @PutMapping
    public ResponseEntity<Barber> updateBarber(@RequestBody Barber updatedBarber) {

        Barber barber = barberService.updateBarber(updatedBarber);

        return ResponseEntity.ok(barber);
    }

    // Endpoint para vincular um serviço a um barbeiro
    @PostMapping("/{barberId}/services/{serviceId}")
    public ResponseEntity<String> assignServiceToBarber(@PathVariable int barberId, @PathVariable int serviceId) {

        barberService.assignServiceToBarber(barberId, serviceId);
        return ResponseEntity.ok("Service assigned to barber successfully");
    }

    //Delete barber
    @DeleteMapping("/{barberId}")
    public ResponseEntity<String> deleteBarber(@PathVariable int barberId) {

        barberService.deleteBarber(barberId);

        return ResponseEntity.ok("Deleted barber id - " + barberId);
    }

}
