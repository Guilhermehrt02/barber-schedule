package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Integer> {
    Optional<Barber> findByEmail(String email);

    List<Barber> findByServices_Id(int serviceId);
    // This interface extends JpaRepository, which is a Spring Data JPA interface that provides CRUD methods
}
