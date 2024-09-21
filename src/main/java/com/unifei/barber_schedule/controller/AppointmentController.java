package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.domain.barber.Barber;
import com.unifei.barber_schedule.service.AppointmentService;
import com.unifei.barber_schedule.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private BarberService barberService;

    @Autowired
    public AppointmentController (AppointmentService appointmentService,
                                  BarberService barberService) {
        this.appointmentService = appointmentService;
        this.barberService = barberService;
    }

    // Obter horários disponíveis para um barbeiro em uma data específica
    @GetMapping("/available-times")
    public ResponseEntity<List<LocalTime>> getAvailableTimes(@RequestParam int barberId, @RequestParam String date, @RequestParam int serviceDuration) {

        Barber barber = barberService.getBarberById(barberId);
        LocalDate appointmentDate = LocalDate.parse(date);
        List<LocalTime> availableTimes = appointmentService.getAvailableTimesForBarber(barber, appointmentDate, serviceDuration);

        return ResponseEntity.ok(availableTimes);
    }


    // Criar agendamento (pode ser acessado por clientes e admin)
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment newAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }

    // Obter agendamento por ID (clientes podem acessar os seus próprios, admin pode acessar qualquer um)
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    // Obter todos os agendamentos de um cliente
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByClientId(@PathVariable int clientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByClientId(clientId);
        return ResponseEntity.ok(appointments);
    }

    // Atualizar agendamento
    @PutMapping()
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointmentDetails) {
        Appointment updatedAppointment = appointmentService.updateAppointment(appointmentDetails);
        return ResponseEntity.ok(updatedAppointment);
    }

    // Deletar agendamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
