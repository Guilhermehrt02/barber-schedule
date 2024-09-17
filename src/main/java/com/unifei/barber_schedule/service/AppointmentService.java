package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.entity.Barber;
import com.unifei.barber_schedule.entity.Client;
import com.unifei.barber_schedule.repository.AppointmentRepository;
import com.unifei.barber_schedule.repository.BarberRepository;
import com.unifei.barber_schedule.repository.ClientRepository;
import com.unifei.barber_schedule.repository.ServiceRepository;
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
    private final ServiceRepository serviceRepository;
    private final BarberRepository barberRepository;
    private final ClientRepository clientRepository;

    // Define o horário de trabalho do barbeiro (por exemplo, das 09:00 às 18:00)
    LocalTime startTime = LocalTime.of(9, 0);
    LocalTime endTime = LocalTime.of(18, 0);

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              ServiceRepository serviceRepository,
                              BarberRepository barberRepository,
                              ClientRepository clientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
        this.barberRepository = barberRepository;
        this.clientRepository = clientRepository;
    }
    public List<Appointment> getAppointmentsByClientId(int clientId) {
        return appointmentRepository.findByClient_Id(clientId);
    }

    public Appointment getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found."));
    }

    public Appointment createAppointment(Appointment appointment) {
        validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        validateAppointment(appointment);
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

    private void validateAppointment(Appointment appointment) {

        // Validação para garantir que o barbeiro não tenha outro agendamento no mesmo horário

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



        // Validação para garantir que o serviço existe
        if (!serviceRepository.existsById(appointment.getService().getId())) {
            throw new IllegalArgumentException("Serviço não encontrado.");
        }

        // Validação para garantir que o cliente existe
        if (!clientRepository.existsById(appointment.getClient().getId())) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        // Validação para garantir que o barbeiro existe
        if (!barberRepository.existsById(appointment.getBarber().getId())) {
            throw new IllegalArgumentException("Barbeiro não encontrado.");
        }
    }

    public List<LocalTime> getAvailableTimesForBarber(Barber barber, LocalDate date, int serviceDuration) {

        // Horário de início do expediente do barbeiro

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
