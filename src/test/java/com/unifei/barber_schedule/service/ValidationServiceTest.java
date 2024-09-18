package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.entity.Barber;
import com.unifei.barber_schedule.entity.Client;
import com.unifei.barber_schedule.entity.Service;
import com.unifei.barber_schedule.repository.AppointmentRepository;
import com.unifei.barber_schedule.repository.BarberRepository;
import com.unifei.barber_schedule.repository.ClientRepository;
import com.unifei.barber_schedule.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class ValidationServiceTest {

    // The Mockito creates a fake object of the class with controlled behavior
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private BarberRepository barberRepository;

    // The @InjectMocks creates an instance of the class and injects the mocks that are created with the @Mock annotation
    @InjectMocks
    private ValidationService validationService;

    // Call the method before each test
    @BeforeEach
    void setup(){

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should allow the creation of an appointment with a valid date and time")
    void validateAppointmentSuccessfully() throws Exception {

        LocalDate date = LocalDate.of(2024, 9, 30);
        LocalTime time = LocalTime.of(10, 0);
        Service serviceObj = new Service("Corte de Cabelo",35.0,40);  // Supondo o construtor de Service
        Client clientObj = new Client("Cliente teste", "cliente@teste.com", "1234", "1234");  // Supondo o construtor de Client
        Barber barberObj = new Barber("Barbeiro Teste", "barbeiro@teste.com", "1234", "1234");  // Supondo o construtor de Barber

        Appointment appointment = new Appointment(date, time, serviceObj, clientObj, barberObj);

        when(serviceRepository.existsById(serviceObj.getId())).thenReturn(true);
        when(clientRepository.existsById(clientObj.getId())).thenReturn(true);
        when(barberRepository.existsById(barberObj.getId())).thenReturn(true);

        when(appointmentRepository.findByBarberAndDate(barberObj, date)).thenReturn(Collections.emptyList()); // Sem conflito

        validationService.validateAppointment(appointment);
    }

    @Test
    @DisplayName("Should not allow the creation of an appointment with conflicting time")
    void validateAppointmentWithConflictingTime() throws Exception {
        // Configuração dos dados de entrada
        LocalDate date = LocalDate.of(2024, 9, 30);
        LocalTime time = LocalTime.of(10, 0);
        Service serviceObj = new Service("Corte de Cabelo", 35.0, 40);
        Client clientObj = new Client("Cliente Teste", "cliente@teste.com", "1234", "1234");
        Barber barberObj = new Barber("Barbeiro Teste", "barbeiro@teste.com","1234", "1234");

        Appointment newAppointment = new Appointment(date, time, serviceObj, clientObj, barberObj);

        // Mockando um agendamento existente que causa conflito
        Appointment conflictingAppointment = new Appointment(date, LocalTime.of(9, 30), serviceObj, clientObj, barberObj);

        when(serviceRepository.existsById(serviceObj.getId())).thenReturn(true);
        when(clientRepository.existsById(clientObj.getId())).thenReturn(true);
        when(barberRepository.existsById(barberObj.getId())).thenReturn(true);
        when(appointmentRepository.findByBarberAndDate(barberObj, date)).thenReturn(Collections.singletonList(conflictingAppointment));

        // Espera que a validação lance uma exceção de conflito de horário
        assertThrows(IllegalArgumentException.class, () -> validationService.validateAppointment(newAppointment));
    }

    @Test
    @DisplayName("Should not allow the creation of an appointment if service does not exist")
    void validateAppointmentServiceNotFound() throws Exception {
        // Configuração dos dados de entrada
        LocalDate date = LocalDate.of(2024, 9, 30);
        LocalTime time = LocalTime.of(10, 0);
        Service serviceObj = new Service("Corte de Cabelo", 35.0, 40);
        Client clientObj = new Client("Cliente Teste", "cliente@teste.com", "1234", "1234");
        Barber barberObj = new Barber("Barbeiro Teste", "barbeiro@teste.com","1234", "1234");

        Appointment appointment = new Appointment(date, time, serviceObj, clientObj, barberObj);

        // Mockando o comportamento dos repositórios para serviço inexistente
        when(serviceRepository.existsById(serviceObj.getId())).thenReturn(false);

        // Espera que a validação lance uma exceção de serviço não encontrado
        assertThrows(IllegalArgumentException.class, () -> validationService.validateAppointment(appointment));
    }
}