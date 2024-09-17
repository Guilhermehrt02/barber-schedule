package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.entity.Barber;
import com.unifei.barber_schedule.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    //List<Appointment> findByBarberId(int barberId); // This method will be used to get all appointments from a specific barber

    //boolean existsByBarberAndDateAndTime(Barber barber, String date, String time);

    List<Appointment> findByClient_Id(int clientId);

    List<Appointment> findByBarberAndDate(Barber barber, LocalDate date);


}
