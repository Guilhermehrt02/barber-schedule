package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber, Integer> {
    // This interface extends JpaRepository, which is a Spring Data JPA interface that provides CRUD methods
}
