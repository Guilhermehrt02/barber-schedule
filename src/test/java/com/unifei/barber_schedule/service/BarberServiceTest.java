package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.domain.barber.Barber;
import com.unifei.barber_schedule.entity.Service;
import com.unifei.barber_schedule.repository.BarberRepository;
import com.unifei.barber_schedule.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class BarberServiceTest {

    @Mock
    private BarberRepository barberRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private BarberService barberService;

    // Call the method before each test
    @BeforeEach
    void setup(){

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should allow the creation of a barber with a valid email")
    void registerBarberSuccessfully() throws Exception{

        Barber barber = new Barber("test Barber", "barber@teste.com", "1234", "1234");

        when(barberRepository.findByEmail(barber.getEmail())).thenReturn(Optional.empty());

        barberService.registerBarber(barber);
    }

    @Test
    @DisplayName("Should not allow the creation of a barber with an existing email")
    void registerBarberWithExistingEmail() throws Exception{

        Barber barber = new Barber("test Barber", "barber@teste.com", "1234", "1234");

        when(barberRepository.findByEmail(barber.getEmail())).thenReturn(Optional.of(barber));

        // The assertThrows method checks if the exception is thrown
        assertThrows(IllegalArgumentException.class, () -> barberService.registerBarber(barber));
    }

    @Test
    @DisplayName("Should allow the update of a barber with a valid email")
    void updateBarber() {

        Barber barber = new Barber("test Barber", "barber@test.com", "1234", "1234");

        when(barberRepository.findByEmail(barber.getEmail())).thenReturn(Optional.of(barber));
        when(barberRepository.findById(barber.getId())).thenReturn(Optional.of(barber));

        barber.setName("updated Barber");
        barber.setEmail("updated@test.com");

        barberService.updateBarber(barber);
    }

    @Test
    @DisplayName("Should allow the assignment of a service to a barber")
    void assignServiceToBarberSuccessfully() throws Exception{

        Barber barber = new Barber("test Barber", "barber@test.com", "1234", "1234");
        com.unifei.barber_schedule.entity.Service service = new Service("Corte de Cabelo", 35.0, 40);

        when(barberRepository.findById(barber.getId())).thenReturn(Optional.of(barber));
        when(serviceRepository.findById(service.getId())).thenReturn(Optional.of(service));

        barberService.assignServiceToBarber(barber.getId(), service.getId());

    }

    @Test
    @DisplayName("Should not allow the assignment of an existing service to a barber")
    void assignExistingServiceToBarber() throws Exception{

        Barber barber = new Barber("test Barber", "barber@test.com", "1234", "1234");
        com.unifei.barber_schedule.entity.Service service = new Service("Corte de Cabelo", 35.0, 40);
        barber.setServices(Collections.singletonList(service));

        when(barberRepository.findById(barber.getId())).thenReturn(Optional.of(barber));
        when(serviceRepository.findById(service.getId())).thenReturn(Optional.of(service));

        assertThrows(IllegalArgumentException.class, () -> barberService.assignServiceToBarber(barber.getId(), service.getId()));

    }
}