package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByClientId(int clientId); // This method will be used to get all appointments from a specific client
    List<Appointment> findByBarberId(int barberId); // This method will be used to get all appointments from a specific barber
}
