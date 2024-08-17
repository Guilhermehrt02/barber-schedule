package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    // This interface extends JpaRepository, which is a Spring Data JPA interface that provides CRUD methods
}
