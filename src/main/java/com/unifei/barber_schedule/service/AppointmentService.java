package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    @Autowired // Constructor-based dependency injection. Be able to use the methods from the repository without creating a new instance of it.
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    // find all appointments
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    // find appointment by id
    public Appointment findById(int id){
        Optional<Appointment> result = appointmentRepository.findById(id);

        Appointment appointment = null;

        if (result.isPresent()) {
            appointment = result.get();
        } else {
            throw new RuntimeException("Appointment id not found - " + id);
        }

        return appointment;
    }

    // save appointment
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // delete appointment
    public void deleteById(int id){
        appointmentRepository.deleteById(id);
    }
}

