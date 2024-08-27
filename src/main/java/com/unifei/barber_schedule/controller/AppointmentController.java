package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController (AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Here we can create the methods that will be called by the client side.

    //Get all appointments
    @GetMapping
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    //Get appointment by id
    @GetMapping("/{appointmentId}")
    public Appointment getAppointment(@PathVariable int appointmentId) {

        Appointment appointment = appointmentService.findById(appointmentId);

        if (appointment == null) {
            throw new RuntimeException("Appointment id not found - " + appointmentId);
        }
        return appointment;
    }

    //Create appointment
    @PostMapping
    public Appointment CreateAppointment(@RequestBody Appointment appointment) {

        appointment.setId(0); // This way we can save a new appointment instead of updating an existing one
        Appointment dbAppointment = appointmentService.save(appointment);

        return dbAppointment;
    }

    //Update appointment
    @PutMapping
    public Appointment updateAppointment(@RequestBody Appointment appointment) {

        Appointment dbAppointment = appointmentService.save(appointment);

        return dbAppointment;
    }

    //Delete appointment
    @DeleteMapping("/{appointmentId}")
    public String deleteAppointment(@PathVariable int appointmentId) {

        Appointment appointment = appointmentService.findById(appointmentId);

        if (appointment == null) {
            throw new RuntimeException("Appointment id not found - " + appointmentId);
        }

        appointmentService.deleteById(appointmentId);

        return "Deleted appointment id - " + appointmentId;
    }


}
