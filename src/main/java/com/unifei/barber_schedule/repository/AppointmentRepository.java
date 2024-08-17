package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // This interface extends JpaRepository, which is a Spring Data JPA interface that provides CRUD methods
}
