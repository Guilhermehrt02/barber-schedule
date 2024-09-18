package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.entity.Barber;
import com.unifei.barber_schedule.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ValidationService validationService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              ValidationService validationService) {
        this.appointmentRepository = appointmentRepository;
        this.validationService = validationService;
    }

    public List<Appointment> getAppointmentsByClientId(int clientId) {
        return appointmentRepository.findByClient_Id(clientId);
    }

    public Appointment getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found."));
    }

    public Appointment createAppointment(Appointment appointment) {
        validationService.validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        validationService.validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(int appointmentId) {
        Appointment appointment = getAppointmentById(appointmentId);
        appointmentRepository.delete(appointment);
    }

    public void deleteAppointmentsByClientId(int clientId) {
        List<Appointment> appointments = getAppointmentsByClientId(clientId);
        appointmentRepository.deleteAll(appointments);
    }


    public List<LocalTime> getAvailableTimesForBarber(Barber barber, LocalDate date, int serviceDuration) {

        // Define o horário de trabalho do barbeiro (por exemplo, das 09:00 às 18:00)
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        // Lista para armazenar os horários disponíveis
        List<LocalTime> availableTimes = new ArrayList<>();

        // Recupera os agendamentos do barbeiro no dia especificado
        List<Appointment> appointments = appointmentRepository.findByBarberAndDate(barber, date);

        // Gera os possíveis horários a cada intervalo da duração do serviço
        while (!startTime.isAfter(endTime)) {
            boolean isAvailable = true;

            LocalTime slotEndTime = startTime.plusMinutes(serviceDuration);

            // Verifica se o intervalo atual conflita com algum agendamento existente
            for (Appointment appointment : appointments) {
                LocalDateTime appointmentStartTime = LocalDateTime.of(appointment.getDate(), appointment.getTime());
                LocalDateTime appointmentEndTime = appointmentStartTime.plusMinutes(appointment.getService().getDuration());

                // Verifica se o horário de início ou fim do intervalo conflita
                if (LocalDateTime.of(date, startTime).isBefore(appointmentEndTime) &&
                        LocalDateTime.of(date, slotEndTime).isAfter(appointmentStartTime)) {
                    isAvailable = false;
                    break;
                }
            }

            // Adiciona o horário à lista se não houver conflito
            if (isAvailable) {
                availableTimes.add(startTime);
            }

            // Avança para o próximo intervalo
            startTime = startTime.plusMinutes(serviceDuration);
        }

        return availableTimes;
    }
}
