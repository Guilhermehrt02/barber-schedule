package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired // field-based dependency injection. Be able to use the methods from the repository
    private AppointmentRepository appointmentRepository;

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    // find all appointments
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    // find appointment by id
    public Optional<Appointment> findById(int id){
        return appointmentRepository.findById(id);
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

