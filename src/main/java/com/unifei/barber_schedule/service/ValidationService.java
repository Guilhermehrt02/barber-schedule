package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.repository.AppointmentRepository;
import com.unifei.barber_schedule.repository.BarberRepository;
import com.unifei.barber_schedule.repository.ClientRepository;
import com.unifei.barber_schedule.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValidationService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceRepository serviceRepository;
    private final ClientRepository clientRepository;
    private final BarberRepository barberRepository;

    @Autowired
    public ValidationService(AppointmentRepository appointmentRepository, ServiceRepository serviceRepository, ClientRepository clientRepository, BarberRepository barberRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
        this.clientRepository = clientRepository;
        this.barberRepository = barberRepository;
    }

    public void validateAppointment(Appointment appointment) {

        // Validação para garantir que o serviço existe
        if (!serviceRepository.existsById(appointment.getService().getId())) {
            throw new IllegalArgumentException("Service not found.");
        }

        // Validação para garantir que o cliente existe
        if (!clientRepository.existsById(appointment.getClient().getId())) {
            throw new IllegalArgumentException("Client not found.");
        }

        // Validação para garantir que o barbeiro existe
        if (!barberRepository.existsById(appointment.getBarber().getId())) {
            throw new IllegalArgumentException("Barber not found.");
        }


        // Verifica conflito de horário

        // Horário de início do novo agendamento
        LocalDateTime newStartTime = LocalDateTime.of(appointment.getDate(), appointment.getTime());

        // Calcula o horário de término do novo agendamento baseado na duração do serviço
        LocalDateTime newEndTime = newStartTime.plusMinutes(appointment.getService().getDuration());

        // Verifica se há um agendamento existente com o mesmo barbeiro que tenha conflito de horário
        List<Appointment> existingAppointments = appointmentRepository.findByBarberAndDate(appointment.getBarber(), appointment.getDate());

        for (Appointment existingAppointment : existingAppointments) {
            LocalDateTime existingStartTime = LocalDateTime.of(existingAppointment.getDate(), existingAppointment.getTime());
            LocalDateTime existingEndTime = existingStartTime.plusMinutes(existingAppointment.getService().getDuration());

            // Verifica se há um conflito de horário
            if (newStartTime.isBefore(existingEndTime) && newEndTime.isAfter(existingStartTime)) {
                throw new IllegalArgumentException("The barber already has an appointment that conflicts with the selected time.");
            }
        }

    }
}
